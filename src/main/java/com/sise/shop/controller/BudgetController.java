package com.sise.shop.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import com.sise.shop.entity.EchartsEntityParam;
import com.sise.shop.service.IBudgetService;
import com.sise.shop.utilis.result.Result;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        Result result = iBudgetService.saveBudget(map);
        return result;
    }


    @CrossOrigin
    @RequestMapping(value = "/getBubgetData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public  List<Budget> getBubgetData( @RequestBody Map map){
        String userId = (String) map.get("userId");
        List<Budget> budgetList=iBudgetService.selectByUserId(userId);
        return budgetList;
    }

    /**
     * 获取收支信息生成图表数据---最近7天信息
     * @param map
     * @return
     */

    @CrossOrigin
    @RequestMapping(value = "/getEchartsInComeData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public  List<EchartsEntityParam> getEchartsInComeData( @RequestBody Map map){
        String userId = (String) map.get("userId");
        List<Budget> budgetList=iBudgetService.selectByUserIdLimit7(userId);
        List<EchartsEntityParam> echartsList=new ArrayList<>();
        for (Budget budget:budgetList){
            EchartsEntityParam echarts = new EchartsEntityParam();
            String createTime = budget.getCreateTime();
            String[] yyyyDDmm = createTime.split(" ");  //切割时间为yyyyDDmm hh:mm:ss
            echarts.setName(yyyyDDmm[0]);
            echarts.setValue(budget.getInSum());
            echartsList.add(echarts);
        }
        return echartsList;
    }

    @CrossOrigin
    @RequestMapping(value = "/getEchartsOutComeData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public  List<EchartsEntityParam> getEchartsOutComeData( @RequestBody Map map){
        String userId = (String) map.get("userId");
        List<Budget> budgetList=iBudgetService.selectByUserIdLimit7(userId);
        List<EchartsEntityParam> echartsList=new ArrayList<>();
        for (Budget budget:budgetList){
            EchartsEntityParam echarts = new EchartsEntityParam();
            String createTime = budget.getCreateTime();
            String[] yyyyDDmm = createTime.split(" ");  //切割时间为yyyyDDmm hh:mm:ss
            echarts.setName(yyyyDDmm[0]);
            echarts.setValue(budget.getOutSum());
            echartsList.add(echarts);
        }
        return echartsList;
    }
    /**
     * 查询收入支出分页数据
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Budget> selectPage( @RequestBody Map map){
        Budget budget = new Budget();
        String userId = (String) map.get("userId");
        budget.setUserId(userId);
        Page<Budget> page = new Page<Budget>();
        EntityWrapper<Budget> eWrapper = new EntityWrapper<Budget>(budget,"8");
        String selectWord = MapUtils.getString(map, "selectWord");              //判断是否有模糊查询参数
        if(!StringUtils.isEmpty(selectWord)){
            eWrapper.like("memo",selectWord,SqlLike.DEFAULT);
        }
        Page<Budget> budgetList = budget.selectPage(page,eWrapper);
        return  budgetList;
    }
}
