package com.sise.shop.service;

import com.sise.shop.entity.Goods;
import com.baomidou.mybatisplus.service.IService;

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
     * @param userId
     * @return
     */
    List<Goods> getGoodsInfo(Map map);
}
