package com.sise.shop.controller;


import com.alibaba.fastjson.JSONObject;
import com.sise.shop.entity.Income;
import com.sise.shop.service.IIncomeService;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 支出表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    private IIncomeService iIncomeService;

    //Memo查询标题，title：keyWord

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Income> search(@RequestBody JSONObject map){
        String title = MapUtils.getString(map, "title");
        List<Income> result = iIncomeService.selectTitle(title);
        return result;
    }
    @CrossOrigin
    @RequestMapping(value = "/saveTitleAndAnswer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveTitleAndAnswer(@RequestBody Map map){
        String title = MapUtils.getString(map, "question");
        String answer = MapUtils.getString(map, "answer");
        Income income=new Income();
        income.setTitle(title);
        income.setMemo(answer);
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        income.setIncomeId(id);
         iIncomeService.insert(income);

        return null;
    }
}
