package com.sise.shop.service.impl;

import com.sise.shop.entity.Goods;
import com.sise.shop.mapper.GoodsMapper;
import com.sise.shop.service.IGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

    @Override
    public List<Goods> getGoodsInfo(Map map) {

        List<Goods> list = goodsMapper.selectByMap(map);
        return list;

    }

    @Override
    public void saveGoods(Map map) throws InvocationTargetException, IllegalAccessException {
        String userId = shopUtils.getUserId(map);
        String uuid = shopUtils.getUuid();
        Map goodsMap= (Map) map.get("form");
        String goodsId = MapUtils.getString(goodsMap, "goodsId");   //取出唯一Id判断是需要更新还是新增数据
        Goods goods=new Goods();
        BeanUtils.populate(goods,goodsMap);
        Integer price = goods.getPrice();
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
