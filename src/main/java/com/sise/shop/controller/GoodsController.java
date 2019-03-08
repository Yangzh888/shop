package com.sise.shop.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Goods;
import com.sise.shop.entity.GoodsParam;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.service.IGoodsService;
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
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品出入库表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping("/api/goods")
public class GoodsController {
    /**
     * 获取商品记录Serviec
     */
    @Autowired
    private IGoodsService iGoodsService;
    /**
     * 获取配置商品的Serviec
     */
    @Autowired
    private IGoodsinfoService iGoodsinfoService;

    /**
     * 获取商品的信息
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Goods> getGoodsInfo(@RequestBody Map map) {
        Goods goods = new Goods();
        String userId = shopUtils.getUserId(map);
        goods.setUserId(userId);
        Integer current = shopUtils.getCurrentByMap(map);
        Page<Goods> page = new Page<Goods>(current, 10);
        EntityWrapper<Goods> wrapper = new EntityWrapper<>(goods);
        String selectWord = MapUtils.getString(map, "selectWord");
        String selectType = MapUtils.getString(map, "selectType");
        if (!StringUtils.isEmpty(selectWord)) {
            wrapper.like("tradeName", selectWord, SqlLike.DEFAULT);
            wrapper.or();
            wrapper.like("identifier", selectWord, SqlLike.DEFAULT);
        }
        if (!StringUtils.isEmpty(selectType)) {
            if (selectType.equals("come")) {
                wrapper.eq("status", "come");
            } else if (selectType.equals("out")) {
                wrapper.eq("status", "out");
            } else {

            }
        }
        wrapper.orderBy(true, "createTime", false);
        Page<Goods> list = goods.selectPage(page, wrapper);
        for (Goods g : list.getRecords()) {
            if (g.getStatus().equals("come")) {
                g.setStatusToString("入库");
            } else
                g.setStatusToString("出库");
        }
        return list;
    }

    /**
     * 保存商品出入库的信息
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/saveGoods", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveGoods(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {

        Result result = iGoodsService.saveGoods(map);
        return result;
    }

    /**
     * 删除出入库记录
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteGoods(@RequestBody Map map) {
        String goodsId = MapUtils.getString(map, "goodsId");
        Goods goods = iGoodsService.selectById(goodsId);
        boolean b = iGoodsService.rollBackNumber(goodsId, goods.getGoodsInfoId());      //先回滚数据
        if (b) {
            boolean success = iGoodsService.deleteById(goodsId);
            if (success) {
                return ResultFactory.buildSuccessResult("删除成功");
            } else
                return ResultFactory.buildFailResult("失败");
        } else {
            return ResultFactory.buildFailResult("删除失败，可能是该商品库存数量小于该条记录的进货数量");
        }
    }

    /**
     * 计算所有商品价值
     */
    @CrossOrigin
    @RequestMapping(value = "/getSumByUserId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result getSumByUserId(@RequestBody Map map) {

        String userId = shopUtils.getUserId(map);
        Goods goods = new Goods();
        goods.setUserId(userId);
        List<Goods> list = iGoodsService.selectByMap(map);
        int sum = 0;
        for (Goods g : list
                ) {
            sum += g.getSum();
        }
        return ResultFactory.buildSuccessResult(sum);
    }

    /**
     * 获取商品信息-新增记录时用于选择已配置好的商品
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getGoodsInfoList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<GoodsParam> getGoodsInfoList(@RequestBody Map map) {
        List<GoodsParam> goodsParamList = new ArrayList<>();
        List<Goodsinfo> list = iGoodsinfoService.selectByMap(map);
        for (Goodsinfo goodsinfo : list) {                                  //整理好数据返回给前端
            GoodsParam test = new GoodsParam();
            test.setValue(goodsinfo.getGoodsInfoId());                         //value和label供前端选择
            test.setLabel(goodsinfo.getTradeName());
            goodsParamList.add(test);
        }
        return goodsParamList;
    }
}
