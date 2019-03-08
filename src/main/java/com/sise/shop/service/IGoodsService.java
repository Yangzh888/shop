package com.sise.shop.service;

import com.sise.shop.entity.Goods;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 根据ID获取商品的信息
     * @param map
     * @return
     */
    List<Goods> getGoodsInfo(Map map);

    /**
     * 保存或者更新商品出入库记录
     * @param map
     */
    Result saveGoods(Map map) throws InvocationTargetException, IllegalAccessException;
     int getGoodsNumber(String userId);
     int getGoodsOutNumber(String userId);
     int getGoodsOutTotal(String userId);

    /**
     * 获取订单分析数据
     * @param userId
     * @return
     */
    List<Map> getOrderInfo(String userId);

    /**
     * 回滚商品数量
     * @param goodsId
     * @param goodsInfoId
     * @return
     */
    public boolean rollBackNumber(String goodsId,String goodsInfoId);
}
