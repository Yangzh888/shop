package com.sise.shop.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.*;
import com.sise.shop.service.IWholesalerService;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 批发商表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-14
 */
@Controller
@RequestMapping("/api/wholesaler")
public class WholesalerController {
    @Autowired
private IWholesalerService iWholesalerService;
    @CrossOrigin
    @RequestMapping(value = "/getWholesaler", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Wholesaler> getWholesaler(@RequestBody Map map){
        Wholesaler wholesaler=new Wholesaler();
        String userId = shopUtils.getUserId(map);
        wholesaler.setUserId(userId);
        wholesaler.setStatus(MapUtils.getString(map, "status"));  //区分是批发商还是客户
        Page<Wholesaler> page=new Page<>();
        EntityWrapper<Wholesaler> eWrapper=new EntityWrapper<>(wholesaler);
        String selectWord= MapUtils.getString(map,"selectWord");
        if(!StringUtils.isEmpty(selectWord)){        //存在关键字搜索即模糊查询
            eWrapper.like("linkMan",selectWord, SqlLike.DEFAULT);
            eWrapper.or();
            eWrapper.like("wholesalerName",selectWord, SqlLike.DEFAULT);
        }
        Page<Wholesaler> wholesalerList = wholesaler.selectPage(page,eWrapper);
        return wholesalerList;
    }
    /**
     * 保存或更新批发商的信息
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/saveWholesaler", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveWholesaler(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        boolean b = iWholesalerService.saveWholesaler(map);
        if(b){
            return ResultFactory.buildSuccessResult("执行成功");
        }else{
            return ResultFactory.buildFailResult("执行失败");
        }
    }
    /**
     * 获取批发商信息用于选择已配置好的商品
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value ="/getWholesalerList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<WholesalerParam> getWholesalerList(@RequestBody Map map){
        List<Wholesaler> list = iWholesalerService.selectByMap(map);
        List<WholesalerParam> paramsList=new ArrayList<>();
        for (Wholesaler wholesaler:list) {
            WholesalerParam wholesalerParam=new WholesalerParam();
            wholesalerParam.setLabel(wholesaler.getWholesalerName());
            wholesalerParam.setValue(wholesaler.getWholesalerName());
            paramsList.add(wholesalerParam);
        }
        return paramsList;
    }
}
