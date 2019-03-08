package com.sise.shop.service;

import com.sise.shop.entity.Wholesaler;
import com.baomidou.mybatisplus.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
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

    /**
     * 查询所有客户批发商+用户的数量
     */
    Integer getAllWholesalerNumber(String userId);
    /**
     * 查询客户的数量
     */
    Integer getCustmerNumber(String userId);
    /**
     * 查询批发商的数量
     */
    Integer getWholesalerNumber(String userId);
    /**
     * 查询每个月客户的数量
     */
    List<Map> getWholesalerInfoList(String userId);


}
