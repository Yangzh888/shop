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
}
