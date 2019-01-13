package com.sise.shop.service.impl;

import com.sise.shop.entity.Budget;
import com.sise.shop.mapper.BudgetMapper;
import com.sise.shop.service.IBudgetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
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

    @Autowired
    private IBudgetService iBudgetService;
    @Override
    public Result saveBudget(Map map) {
        String userId = (String) map.get("userId");
        Map formMap = (Map) map.get("form");
        String  creatTime = (String) formMap.get("createTime");
        int inSum= Integer.parseInt((String) formMap.get("inSum"));
        int outSum = Integer.parseInt((String) formMap.get("outSum"));
        String memo= (String) formMap.get("memo");
        Budget budget = new Budget();
        budget.setUserId(userId);
        budget.setCreateTime(creatTime);
        budget.setInSum(inSum);
        budget.setOutSum(outSum);
        budget.setMemo(memo);
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        budget.setBudgetId(id);
        iBudgetService.insert(budget);
        return ResultFactory.buildSuccessResult("新增成功");
    }
}
