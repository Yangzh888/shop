package com.sise.shop.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Others;
import com.sise.shop.entity.Userinfo;
import com.sise.shop.service.IGoodsService;
import com.sise.shop.utilis.shopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private IGoodsService iGoodsService;
    /**
     * 获取商品的信息
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Goods>  getGoodsInfo(@RequestBody Map map){
        Goods goods=new Goods();
        String userId = shopUtils.getUserId(map);
        goods.setUserId(userId);
        Page<Goods> page = new Page<Goods>();
        EntityWrapper<Goods> wrapper=new EntityWrapper<>(goods);
        Page<Goods> list=goods.selectPage(page,wrapper);
        return list;
    }
}
