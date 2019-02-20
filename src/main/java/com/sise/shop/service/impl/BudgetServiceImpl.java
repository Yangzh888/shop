package com.sise.shop.service.impl;

import com.sise.shop.entity.Budget;
import com.sise.shop.mapper.BudgetMapper;
import com.sise.shop.service.IBudgetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *收支记录Service实现类
 * @author yangzhenhua
 * @since 2019-01-03
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements IBudgetService {
    @Resource
    private BudgetMapper budgetMapper;


    @Override
    public List<Budget> selectByUserId(String userId) {
        return budgetMapper.selectByUserId(userId);
    }

    @Override
    public List<Budget> selectByUserIdLimit7(String userId) {
        return budgetMapper.selectByUserIdLimit7(userId);
    }

    @Override
    public Result deleteBudgetById(String budgetId) {
        Integer integer = budgetMapper.deleteById(budgetId);
        if(integer>0){
            return ResultFactory.buildSuccessResult("删除成功"+integer+"条记录");
        } else {
            return ResultFactory.buildFailResult("删除失败，请重新操作");
        }
    }
    @Override
    public Result saveBudget(Map map) throws InvocationTargetException, IllegalAccessException {
        String userId = (String) map.get("userId");
        Map form = (Map) map.get("form");

        Budget budget = new Budget();
        BeanUtils.populate(budget,form);
        if(budget.getBudgetId().isEmpty()){
        budget.setUserId(userId);
        budget.setBudgetId(shopUtils.getUuid());
        budgetMapper.insert(budget);
            return ResultFactory.buildSuccessResult("新增成功一条数据");}
        else {
            budgetMapper.updateById(budget);
            return ResultFactory.buildSuccessResult("更新成功1条数据");
        }

    }
}
