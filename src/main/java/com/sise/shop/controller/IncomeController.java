package com.sise.shop.controller;


import com.sise.shop.entity.Income;
import com.sise.shop.service.IIncomeService;
import com.sise.shop.utilis.result.Result;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

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
    public List<Income> search(@RequestBody Map map){
        String title = MapUtils.getString(map, "title");
        List<Income> result = iIncomeService.selectTitle(title);
        return result;
    }
}
