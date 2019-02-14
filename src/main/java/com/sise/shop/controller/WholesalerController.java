package com.sise.shop.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.entity.Wholesaler;
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
        Page<Wholesaler> page=new Page<>();
        EntityWrapper<Wholesaler> eWrapper=new EntityWrapper<>(wholesaler);
        String selectWord= MapUtils.getString(map,"selectWord");
        if(!StringUtils.isEmpty(selectWord)){
            eWrapper.like("tradeName",selectWord, SqlLike.DEFAULT);
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
}
