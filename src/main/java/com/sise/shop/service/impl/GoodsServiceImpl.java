package com.sise.shop.service.impl;

import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.mapper.GoodsMapper;
import com.sise.shop.mapper.GoodsinfoMapper;
import com.sise.shop.service.IGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.CommonConstant;
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
    @Override
    public List<Goods> getGoodsInfo(Map map) {

        List<Goods> list = goodsMapper.selectByMap(map);
        return list;

    }

    @Override
    public void saveGoods(Map map) throws InvocationTargetException, IllegalAccessException {
        String userId = shopUtils.getUserId(map);
        String uuid = shopUtils.getUuid();
        Map goodsMap= (Map) map.get(CommonConstant.COMMONCONSTANT_FROM);
        String goodsId = MapUtils.getString(goodsMap, "goodsId");   //取出唯一Id判断是需要更新还是新增数据
        Goods goods=new Goods();
        BeanUtils.populate(goods,goodsMap);
        goods.setGoodsInfoId(goods.getTradeName());               /* 将商品信息Id   set进去     理由看下面注释*/
        if(goods.getStatus().equals("come")){
            //goods.getTradeName()获取到的goodsInfo里面的商品ID。--因为GoodsParam中value存进去的是商品的ID
            Goodsinfo goodsinfo = goodsinfoMapper.selectById(goods.getTradeName());
            goods.setTradeName(goodsinfo.getTradeName());    //查出名称重新SET进去，最后更新当前出入库记录
            Integer number = goodsinfo.getNumber();
            number=number+goods.getQuantity();
            goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(),number);
        }else{
            //goods.getTradeName()获取到的goodsInfo里面的商品ID。--因为GoodsParam中value存进去的是商品的ID
            Goodsinfo goodsinfo = goodsinfoMapper.selectById(goods.getTradeName());
            goods.setTradeName(goodsinfo.getTradeName());    //查出名称重新SET进去，最后更新当前出入库记录
            Integer number = goodsinfo.getNumber();
            number=number-goods.getQuantity();
            goodsinfoMapper.updateGoodsInfoNumber(goodsinfo.getGoodsInfoId(),number);
        }
        Integer price = goods.getPrice();                   //获取单价和数量得到总额
        Integer quantity = goods.getQuantity();
        Integer sum=price*quantity;
        goods.setSum(sum);
        String orderNumber = shopUtils.getOrderNumber(goods.getCreateTime());
        if(goodsId.isEmpty()){
            goods.setIdentifier(orderNumber);
            goods.setGoodsId(uuid);
            goods.setUserId(userId);
            goodsMapper.insert(goods);
        }else{
            goodsMapper.updateById(goods);
        }

    }
    /**
     * 查询所有订单的总数量
     * @param userId
     * @return
     */
    public int getGoodsNumber(String userId){
        Object ob= goodsMapper.getGoodsNumber(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }
    /**
     * 查询所有出库订单总数
     * @param userId
     * @return
     */
    public int getGoodsOutNumber(String userId){
        Object ob= goodsMapper.getGoodsOutNumber(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }
    /**
     * 查询所有出库订单总金额
     * @param userId
     * @return
     */
    public int getGoodsOutTotal(String userId){
        Object ob= goodsMapper.getGoodstOutTotal(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }

    /**
     * 获取订单分析数据
     * @param userId
     * @return
     */
    @Override
    public List<Map> getOrderInfo(String userId) {
        String aboutMonthLabel[]={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};//关于月份标签存放
        int January=0,February=0,March=0,April=0,May=0,June=0,July=0,August=0, September=0,October=0,November=0,December=0;
        List<Map> budgetOutList= goodsMapper.getBudgetOutOrder(userId);
        for (Map m:budgetOutList){
            String identifier = MapUtils.getString(m,"identifier");
            String month = identifier.substring(0, 6);
            Integer sum = MapUtils.getInteger(m,"sum");
            switch(month){
                case "201901":
                    January=January+sum;
                    break;
                case "201902":
                    February=February+sum;
                    break;
                case "201903":
                    March=March+sum;
                    break;
                case "201904":
                    April=April+sum;
                    break;
                case "201905":
                    May=May+sum;
                    break;
                case "201906":
                    June=June+sum;
                    break;
                case "201907":
                    July=July+sum;
                    break;
                case "201908":
                    August=August+sum;
                    break;
                case "201909":
                    September=September+sum;
                    break;
                case "201910":
                    October=October+sum;
                    break;
                case "201911":
                    November=November+sum;
                    break;
                case "201912":
                    December=December+sum;
                    break;
            }
        }
        int[] aboutMonthValue={January,February,March,April,May,June,July,August,September,October,November,December};
            List<Map> resultList=new ArrayList<>();
        for(int i=0;i<12;i++){
            Map m=new HashMap();
            m.put("月份",aboutMonthLabel[i]);
            m.put("金额",aboutMonthValue[i]);
            resultList.add(m);
        }
        return resultList;
    }
}
