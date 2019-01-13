package com.sise.shop.service;

import com.sise.shop.entity.Budget;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-01-03
 */
public interface IBudgetService extends IService<Budget> {

    /**
     * 新增收入支出数据
     * @param map
     * @return
     */
    Result saveBudget(Map map);

    List<Budget> selectByUserId(String userId);

    /**
     * 查询最近7天的收支信息
     * @param userId
     * @return
     */
    List<Budget> selectByUserIdLimit7(String userId);
}
