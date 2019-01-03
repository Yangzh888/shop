package com.sise.shop.controller;


import com.sise.shop.entity.Budget;
import com.sise.shop.service.IBudgetService;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *收入支出
 * @author yangzhenhua
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private IBudgetService iBudgetService;

    @CrossOrigin
    @RequestMapping(value = "/saveBudget", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveBudget( @RequestBody Map map){
        String userId = (String) map.get("userId");
        Map formMap = (Map) map.get("form");
        String  creatTime = (String) formMap.get("createTime");
        int inSum= Integer.parseInt((String) formMap.get("inSum"));
        int outSum = Integer.parseInt((String) formMap.get("outSum"));
        Budget budget = new Budget();
        budget.setUserId(userId);
        budget.setCreateTime(creatTime);
        budget.setInSum(inSum);
        budget.setOutSum(outSum);
        String id = UUID.randomUUID().toString().replaceAll("-", "");
       budget.setBudgetId(id);
       iBudgetService.insert(budget);
        return ResultFactory.buildSuccessResult("新增成功");
    }
}
