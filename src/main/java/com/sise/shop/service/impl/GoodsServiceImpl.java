package com.sise.shop.service.impl;

import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.entity.Wholesaler;
import com.sise.shop.mapper.GoodsMapper;
import com.sise.shop.mapper.GoodsinfoMapper;
import com.sise.shop.service.IGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.CommonConstant;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsinfoMapper goodsinfoMapper;
    @Resource
    private OthersServiceImpl othersService;
    @Resource
    private CustomerServiceImpl customerService;
    @Resource
    private WholesalerServiceImpl wholesalerService;

    @Override
    public List<Goods> getGoodsInfo(Map map) {

        List<Goods> list = goodsMapper.selectByMap(map);
        return list;

    }

    @Override
    public Result saveGoods(Map map) throws InvocationTargetException, IllegalAccessException {
        String userId = shopUtils.getUserId(map);
        String uuid = shopUtils.getUuid();
        Map goodsMap = (Map) map.get(CommonConstant.COMMONCONSTANT_FROM);
        String goodsId = MapUtils.getString(goodsMap, "goodsId");   //取出唯一Id判断是需要更新还是新增数据
        Goods goods = new Goods();
        BeanUtils.populate(goods, goodsMap);
        Integer price = goods.getPrice();                   //获取单价和数量得到总额
        Integer quantity = goods.getQuantity();
        Integer sum = price * quantity;
        goods.setSum(sum);
        String orderNumber = shopUtils.getOrderNumber(goods.getCreateTime());
        if (goodsId.isEmpty()) {
            Goodsinfo goodsinfo = goodsinfoMapper.selectById(goods.getTradeName());//新增操作时前端传来的是goods.getTradeName()商品表里面的ID
            goods.setTradeName(goodsinfo.getTradeName());
            if (goods.getStatus().equals("come")) {
                Integer number = goodsinfo.getNumber();
                number = number + goods.getQuantity();
                goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(), number);

                //关联批发商
                String wholesalerId = MapUtils.getString(goodsMap, "wholesalerId");
                String wholesalerName = wholesalerService.selectById(wholesalerId).getWholesalerName();
                goods.setWholesalerName(wholesalerName);
                goods.setWholesalerId(wholesalerId);
            } else {
                Integer number = goodsinfo.getNumber();
                if (number < goods.getQuantity()) {
                    return ResultFactory.buildFailResult("仓库数量只有" + number + "个,无法出库");
                }
                number = number - goods.getQuantity();
                goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(), number);

                //预警判断
                Integer warmingNumber = goodsinfo.getWarmingNumber();
                if (warmingNumber > number) {//如果现有数量小于预警数量，则新增一条入库待办。
                    othersService.insertReadDo(userId, "《入库通知》", "商品《" + goodsinfo.getTradeName() + "》库存数量已不足，请及时进货",goods.getCreator(),"系统自动生成");
                }
                //关联客户
                String customerId = MapUtils.getString(goodsMap, "customerId");
                String customerName = wholesalerService.selectById(customerId).getWholesalerName();
                goods.setCustomerName(customerName);
                goods.setCustomerId(customerId);
            }
            goods.setGoodsInfoId(goodsinfo.getGoodsInfoId());
            goods.setIdentifier(orderNumber);
            goods.setGoodsId(uuid);
            goods.setUserId(userId);
            goodsMapper.insert(goods);
        } else {
            Goodsinfo goodsinfo = goodsinfoMapper.selectById(goods.getGoodsInfoId());//更新操作时good表里面已有goodsInfoId信息
            //只要是更新数据而不是插入数据，都先将原有数据回滚
//由于数据库实务锁的问题。这里有点麻烦。number1获得的是回滚前的数据，即使调用了回滚方法，数据库也更新了。但是number1获得的依旧是旧的数据
            if (goods.getStatus().equals("come")) {
                this.rollBackNumber(goods.getGoodsId(), goods.getGoodsInfoId());
                Integer oldNumber = goodsMapper.selectById(goods.getGoodsId()).getQuantity();       //该条数据原有的数量

                Integer newNumber = goods.getQuantity();//修改变后的数量
                Integer OldnumberByGoodsInfo = goodsinfo.getNumber(); //商品表里面的原来的数量
                Integer lastNumber=OldnumberByGoodsInfo-oldNumber+newNumber;
                goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(), lastNumber);
            } else {
                Integer number = goodsinfo.getNumber();
                if (number < goods.getQuantity()) {
                    return ResultFactory.buildFailResult("原来的仓库数量不足，只有" + number + "个,无法改为出库");
                }
                number = number - goods.getQuantity();
                goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(), number);
            }
            goodsMapper.updateById(goods);
        }
        return ResultFactory.buildSuccessResult("操作成功");
    }

    /**
     * 回滚商品原来的数量
     */
    public boolean rollBackNumber(String goodsId, String goodsInfoId) {
        Goodsinfo goodsinfo = goodsinfoMapper.selectById(goodsInfoId);
        Goods goods = goodsMapper.selectById(goodsId);
        Integer number = goodsinfo.getNumber();
        if (goods.getStatus().equals("come")){                     //如果是入库即减回去，出库则加回去达到回滚效果
            if (number < goods.getQuantity()) {
                return false;
            } else
                number = number - goods.getQuantity();
        } else {
            number = number + goods.getQuantity();
        }
        goodsinfo.setNumber(number);
        Integer integer = goodsinfoMapper.updateById(goodsinfo);
        return integer > 0 ? true : false;
    }


    /**
     * 查询所有订单的总数量
     *
     * @param userId
     * @return
     */
    public int getGoodsNumber(String userId) {
        Object ob = goodsMapper.getGoodsNumber(userId);
        int b = 0;
        if (ob != null) {
            b = Integer.parseInt(ob.toString());
        }
        return b;
    }

    /**
     * 查询所有出库订单总数
     *
     * @param userId
     * @return
     */
    public int getGoodsOutNumber(String userId) {
        Object ob = goodsMapper.getGoodsOutNumber(userId);
        int b = 0;
        if (ob != null) {
            b = Integer.parseInt(ob.toString());
        }
        return b;
    }

    /**
     * 查询所有出库订单总金额
     *
     * @param userId
     * @return
     */
    public int getGoodsOutTotal(String userId) {
        Object ob = goodsMapper.getGoodstOutTotal(userId);
        int b = 0;
        if (ob != null) {
            b = Integer.parseInt(ob.toString());
        }
        return b;
    }

    /**
     * 获取订单分析数据
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map> getOrderInfo(String userId) {
        String aboutMonthLabel[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};//关于月份标签存放
        int January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0, September = 0, October = 0, November = 0, December = 0;
        List<Map> budgetOutList = goodsMapper.getBudgetOutOrder(userId);
        for (Map m : budgetOutList) {
            String identifier = MapUtils.getString(m, "identifier");
            String month = identifier.substring(0, 6);
            Integer sum = MapUtils.getInteger(m, "sum");
            switch (month) {
                case "201901":
                    January = January + sum;
                    break;
                case "201902":
                    February = February + sum;
                    break;
                case "201903":
                    March = March + sum;
                    break;
                case "201904":
                    April = April + sum;
                    break;
                case "201905":
                    May = May + sum;
                    break;
                case "201906":
                    June = June + sum;
                    break;
                case "201907":
                    July = July + sum;
                    break;
                case "201908":
                    August = August + sum;
                    break;
                case "201909":
                    September = September + sum;
                    break;
                case "201910":
                    October = October + sum;
                    break;
                case "201911":
                    November = November + sum;
                    break;
                case "201912":
                    December = December + sum;
                    break;
            }
        }
        int[] aboutMonthValue = {January, February, March, April, May, June, July, August, September, October, November, December};
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Map m = new HashMap();
            m.put("月份", aboutMonthLabel[i]);
            m.put("金额", aboutMonthValue[i]);
            resultList.add(m);
        }
        return resultList;
    }
}
