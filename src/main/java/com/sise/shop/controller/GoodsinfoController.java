package com.sise.shop.controller;



import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.entity.Income;
import com.sise.shop.service.IGoodsinfoService;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息表-配置商品 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-13
 */
@Controller
@RequestMapping("/api/goodsinfo")
public class GoodsinfoController {

    @Autowired
    private IGoodsinfoService iGoodsinfoService;
    /**
     * 获取配置好的商品信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Goodsinfo> getGoodsInfo(@RequestBody Map map){
        Goodsinfo goodsinfo=new Goodsinfo();
        String userId = shopUtils.getUserId(map);
        Integer current = shopUtils.getCurrentByMap(map);
        goodsinfo.setUserId(userId);
        Page<Goodsinfo> page=new Page<>(current,10);
        EntityWrapper<Goodsinfo> eWrapper=new EntityWrapper<>(goodsinfo);
        String selectWord=MapUtils.getString(map,"selectWord");
        if(!StringUtils.isEmpty(selectWord)){
            eWrapper.like("tradeName",selectWord,SqlLike.DEFAULT);
        }
        Page<Goodsinfo> budgetList = goodsinfo.selectPage(page,eWrapper);
        return budgetList;
    }
    /**
     * 保存配置好的商品信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/saveGoodsInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveGoodsInfo(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        boolean isSuccesse = iGoodsinfoService.saveGoodsInfo(map);
        if (isSuccesse == true) {
            ResultFactory.buildSuccessResult(isSuccesse);
        } else {
            return ResultFactory.buildFailResult("执行失败");
        }
        return null;
    }
    /**
     * 删除商品的信息
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/deleteGoodsInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteGoodsInfo(@RequestBody Map map){

        boolean goodsInfoId = iGoodsinfoService.deleteById(MapUtils.getString(map, "goodsInfoId"));
        if(goodsInfoId){
            return ResultFactory.buildSuccessResult("删除成功");
        }else
            return ResultFactory.buildFailResult("失败");
    }
}
