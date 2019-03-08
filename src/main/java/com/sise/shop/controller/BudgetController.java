package com.sise.shop.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import com.sise.shop.entity.EchartsEntityParam;
import com.sise.shop.service.IBudgetService;
import com.sise.shop.service.IGoodsService;
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
import java.text.ParseException;
import java.util.*;


/**
 * <p>
 * 前端控制器
 * </p>
 * 收入支出
 *
 * @author yangzhenhua
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private IBudgetService iBudgetService;

    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private IWholesalerService iWholesalerService;

    @CrossOrigin
    @RequestMapping(value = "/saveBudget", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveBudget(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        Result result = iBudgetService.saveBudget(map);
        return result;
    }


    @CrossOrigin
    @RequestMapping(value = "/getBubgetData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Budget> getBubgetData(@RequestBody Map map) {
        String userId = (String) map.get("userId");
        List<Budget> budgetList = iBudgetService.selectByUserId(userId);
        return budgetList;
    }

    /**
     * 获取收支信息生成图表数据---最近7天信息
     *
     * @param map
     * @return
     */

 /*   @CrossOrigin
    @RequestMapping(value = "/getEchartsInComeData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<EchartsEntityParam> getEchartsInComeData(@RequestBody Map map) {
        String userId = (String) map.get("userId");
        List<Budget> budgetList = iBudgetService.selectByUserIdLimit7(userId);
        List<EchartsEntityParam> echartsList = new ArrayList<>();
        for (Budget budget : budgetList) {
            EchartsEntityParam echarts = new EchartsEntityParam();
            String createTime = budget.getCreateTime();
            String[] yyyyDDmm = createTime.split(" ");  //切割时间为yyyyDDmm hh:mm:ss
            echarts.setName(yyyyDDmm[0]);
            echarts.setValue(budget.getInSum());
            echartsList.add(echarts);
        }
        return echartsList;
    }*/
    @CrossOrigin
    @RequestMapping(value = "/getEchartsComeAndOutData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getEchartsComeAndOutData(@RequestBody Map map) {
        String userId = (String) map.get("userId");
        List<Map> budgetList = iBudgetService.selectByUserIdLimit7(userId);
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Map m = new HashMap();
            //String time = budgetList.get(i).getCreateTime().substring(5, 9);

            Object createTime = budgetList.get(i).get("createTime");
            String Time = String.valueOf(createTime);
            Object allInSum = budgetList.get(i).get("allInSum");
            int inSum = 0, outSum = 0;
            if (allInSum != null) {
                inSum = Integer.parseInt(allInSum.toString());
            }
            Object allOutSum = budgetList.get(i).get("allOutSum");
            if (allOutSum != null) {
                outSum = Integer.parseInt(allOutSum.toString());
            }
            m.put("日期", Time.substring(5, 10));
            m.put("当天收入", inSum);
            m.put("当天支出", outSum);
            resultList.add(m);
        }
        return resultList;
    }

    /**
     * 查询收入支出分页数据
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Budget> selectPage(@RequestBody Map map) {
        Budget budget = new Budget();
        String userId = (String) map.get("userId");
        budget.setUserId(userId);
        Integer current = shopUtils.getCurrentByMap(map);
        Page<Budget> page = new Page<Budget>(current, 8);
        EntityWrapper<Budget> eWrapper = new EntityWrapper<Budget>(budget, "8");
        String selectWord = MapUtils.getString(map, "selectWord");              //判断是否有模糊查询参数
        if (!StringUtils.isEmpty(selectWord)) {
            eWrapper.like("memo", selectWord, SqlLike.DEFAULT);
        }
        Page<Budget> budgetList = budget.selectPage(page, eWrapper);
        return budgetList;
    }

    /**
     * 查询收入支出分页数据
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/deleteBudgetById", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteBudgetById(@RequestBody Map map) {
        String budgetId = MapUtils.getString(map, "budgetId");
        Result result = iBudgetService.deleteBudgetById(budgetId);
        return result;
    }


    /**
     * 数据分析---获取商铺的信息
     */
    @CrossOrigin
    @RequestMapping(value = "/getShopInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map getShopInfo(@RequestBody Map map) {
        Map ResultMap = new HashMap();
        String userId = shopUtils.getUserId(map);

        int allTradeTotal = iBudgetService.getAllTradeTotal(userId);            //商品总价值
        int allTradeProfitTotal = iBudgetService.getAllTradeProfitTotal(userId);   //目前商品预期利润
        int tradeGetProfitTotal = iBudgetService.getTradeGetProfitTotal(userId);  //已出售商品赚取金额
        /*** -------------------------------------关于钱存放start-----------------------------------------------------------*/
        int aboutMoneysValue[] = {allTradeTotal, allTradeProfitTotal, tradeGetProfitTotal};  //关于钱值存放
        String aboutMoneysLabel[] = {"商品总价值", "目前商品预期利润", "已出售商品赚取金额"};//关于钱标签存放
        List<Map> aboutMoney = new ArrayList<>();          //关于钱的数据
        for (int i = 0; i < 3; i++) {                         //将数据查询出来存放进去
            Map m = new HashMap();
            m.put("label", aboutMoneysLabel[i]);
            m.put("值", aboutMoneysValue[i]);
            aboutMoney.add(m);
        }
        /*** -------------------------------------关于钱存放End-----------------------------------------------------------*/
        /*** -------------------------------------关于物品start-----------------------------------------------------------*/
        int allTradeNumber = iBudgetService.getAllTradeNumber(userId);             //所有商品数量
        int allwholesalerNumber = iBudgetService.getAllwholesalerNumber(userId);   //所有客户数量
        int budgetNumber = iGoodsService.getGoodsNumber(userId);                 //订单数量
        List<Map> aboutGoods = new ArrayList<>();
        int aboutGoodsValue[] = {allTradeNumber, allwholesalerNumber, budgetNumber};  //关于物品值存放
        String aboutGoodsLabel[] = {"所有商品数量", "所有客户数量", "所有订单数量"};//关于物品标签存放
        for (int i = 0; i < 3; i++) {                         //将数据查询出来存放进去
            Map m = new HashMap();
            m.put("label", aboutGoodsLabel[i]);
            m.put("值", aboutGoodsValue[i]);
            aboutGoods.add(m);
        }
        /*** -------------------------------------关于物品end-----------------------------------------------------------*/

        Map shopInfoMap = new HashMap();
        shopInfoMap.put("allTradeTotal", allTradeTotal);
        shopInfoMap.put("allTradeProfitTotal", allTradeProfitTotal);
        shopInfoMap.put("tradeGetProfitTotal", tradeGetProfitTotal);
        shopInfoMap.put("allTradeNumber", allTradeNumber);
        shopInfoMap.put("allwholesalerNumber", allwholesalerNumber);
        shopInfoMap.put("budgetNumber", budgetNumber);

        ResultMap.put("aboutMoney", aboutMoney);
        ResultMap.put("shopInfoMap", shopInfoMap);
        ResultMap.put("aboutGoods", aboutGoods);
        return ResultMap;
    }

    /**
     * 数据分析---订单分析
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map getOrderInfo(@RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        Map ResultMap = new HashMap();
        List<Map> orderInfoList = iGoodsService.getOrderInfo(userId);      //每个月份的出库金额
        ResultMap.put("orderInfoList", orderInfoList);
        Map orderInfo = new HashMap();
        int goodsOutNumber = iGoodsService.getGoodsOutNumber(userId);   //所有出库订单总数
        int goodsOutTotal = iGoodsService.getGoodsOutTotal(userId);     //所有出库订单总金额
        orderInfo.put("goodsOutNumber", goodsOutNumber);
        orderInfo.put("goodsOutTotal", goodsOutTotal);
        ResultMap.put("orderInfo", orderInfo);
        return ResultMap;
    }

    /**
     * 数据分析---客户分析
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getWholesalerInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map getWholesalerInfo(@RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        Map ResultMap = new HashMap();
        Integer allWholesalerNumber = iWholesalerService.getAllWholesalerNumber(userId);      //所有客户包括批发商的数量
        Integer custmerNumber = iWholesalerService.getCustmerNumber(userId);              //客户的数量
        Integer wholesalerNumber = iWholesalerService.getWholesalerNumber(userId);       //批发商的数量
        Map wholesalerInfo = new HashMap();
        wholesalerInfo.put("allWholesalerNumber", allWholesalerNumber);
        wholesalerInfo.put("custmerNumber", custmerNumber);
        wholesalerInfo.put("wholesalerNumber", wholesalerNumber);
        List<Map> wholesalerInfoList = iWholesalerService.getWholesalerInfoList(userId);     //图表的信息
        ResultMap.put("wholesalerInfo", wholesalerInfo);
        ResultMap.put("wholesalerInfoList", wholesalerInfoList);
        return ResultMap;
    }

    /**
     * 首页---季度营业额
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getQuarterInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map> getQuarterInfo(@RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        String year = MapUtils.getString(map, "year");
        List<Map> list = iBudgetService.getQuarterInfo(userId, year);
        return list;
    }


    /**
     * 收支统计---按月统计
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getOneMonthComeAndOut", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result getOneMonthComeAndOut(@RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        String selectMonth = null;
        selectMonth = MapUtils.getString(map, "selectMonth");            //2019年9月--前端对应传来的是2019-8-31日
        if (selectMonth == null||selectMonth=="") {
            Date date = new Date();
            selectMonth = shopUtils.dateTostring(date).substring(0, 7);
        }
        List<Map> resultlist = iBudgetService.getOneMonthComeAndOut(userId, selectMonth);
        if(resultlist.size()==0){
            return ResultFactory.buildFailResult("数据为空");
        }
        return ResultFactory.buildSuccessResult(resultlist);
    }
}
