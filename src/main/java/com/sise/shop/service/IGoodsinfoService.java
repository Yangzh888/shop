package com.sise.shop.service;

import com.sise.shop.entity.Goodsinfo;
import com.baomidou.mybatisplus.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-13
 */
public interface IGoodsinfoService extends IService<Goodsinfo> {

    boolean saveGoodsInfo(Map map) throws InvocationTargetException, IllegalAccessException;

}
