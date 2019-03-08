package com.sise.shop.service;

import com.sise.shop.entity.Budget;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

import java.lang.reflect.InvocationTargetException;
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
    Result saveBudget(Map map) throws InvocationTargetException, IllegalAccessException;

    List<Budget> selectByUserId(String userId);

    /**
     * 查询最近7天的收支信息
     * @param userId
     * @return
     */
    List<Map> selectByUserIdLimit7(String userId);

    /**
     * 通过ID删除该条记录
     * @param budgetId
     * @return
     */
    Result deleteBudgetById(String budgetId);

    int getAllTradeNumber(String userId);
    int getAllTradeTotal(String userId);
    int getAllTradeProfitTotal(String userId);
    int getTradeGetProfitTotal(String userId);
    int getAllwholesalerNumber(String userId);

    /**
     * 获取首页--图表季度的营业额
     * @param userId
     * @return
     */
    List<Map> getQuarterInfo(String userId,String year);

    /**
     * 获取某一年某一月的图表信息
     * @param userId
     * @param date  年月  例如：2019-01
     * @return
     */
    List<Map> getOneMonthComeAndOut(String userId, String date);
}
