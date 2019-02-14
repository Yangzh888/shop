package com.sise.shop.service;

import com.sise.shop.entity.Wholesaler;
import com.baomidou.mybatisplus.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>
 * 批发商表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-14
 */
public interface IWholesalerService extends IService<Wholesaler> {
    /**
     * 保存或者更新批发商信息
     * @param map
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
     boolean saveWholesaler(Map map) throws InvocationTargetException, IllegalAccessException;
}
