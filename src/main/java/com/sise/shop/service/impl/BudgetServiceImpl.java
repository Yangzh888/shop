package com.sise.shop.service.impl;

import com.sise.shop.entity.Budget;
import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.mapper.BudgetMapper;
import com.sise.shop.mapper.GoodsMapper;
import com.sise.shop.mapper.GoodsinfoMapper;
import com.sise.shop.mapper.WholesalerMapper;
import com.sise.shop.service.IBudgetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *收支记录Service实现类
 * @author yangzhenhua
 * @since 2019-01-03
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements IBudgetService {
    @Resource
    private BudgetMapper budgetMapper;
    @Resource
    private GoodsinfoMapper goodsinfoMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private WholesalerMapper wholesalerMapper;




    @Override
    public List<Map> selectByUserIdLimit7(String userId) {
        return budgetMapper.selectByUserIdLimit7(userId);
    }

    @Override
    public Result deleteBudgetById(String budgetId) {
        Integer integer = budgetMapper.deleteById(budgetId);
        if (integer > 0) {
            return ResultFactory.buildSuccessResult("删除成功" + integer + "条记录");
        } else {
            return ResultFactory.buildFailResult("删除失败，请重新操作");
        }
    }

    @Override
    public Result saveBudget(Map map) throws InvocationTargetException, IllegalAccessException {
        String userId = (String) map.get("userId");
        Map form = (Map) map.get("form");
        Budget budget = new Budget();
        BeanUtils.populate(budget, form);
        if (budget.getBudgetId()==null) {
            budget.setUserId(userId);
            budget.setBudgetId(shopUtils.getUuid());
            budgetMapper.insert(budget);
            return ResultFactory.buildSuccessResult("新增成功一条数据");
        } else {
            budgetMapper.updateById(budget);
            return ResultFactory.buildSuccessResult("更新成功1条数据");
        }
    }


    /**
     * 数据分析--------------------------------------------------------------------------------------Star-----------------------------------------------------
     */
    /**
     * 获取商铺商品的数量
     */
    public int getAllTradeNumber(String userId) {
        int Allnumber = goodsinfoMapper.getAllTradeNumber(userId);
        return Allnumber;
    }

    /**
     * 获取商品当前商品的总价值
     * 首先获取goodsInfo表中当前商品含有的商品的信息，
     * 如果数量不为0，通过商品id去出入库表找最新的一条记录的单价。然后相乘获取这个商品的总价
     * 最后将所有商品的总加加起来就获得当前商铺的所有商品的总价了
     */
    public int getAllTradeTotal(String userId) {
        List<Goodsinfo> goodsinfoList = goodsinfoMapper.getAllTrade(userId);
        int Total = 0;
        for (Goodsinfo g : goodsinfoList) {
            if (g.getNumber() > 0) {
                Goods goods = goodsMapper.selectOneById(g.getGoodsInfoId(), userId);
                if(goods!=null){
                    Total = Total + goods.getPrice() * g.getNumber();
                }

            }
        }
        return Total;
    }

    /**
     * 获取当前商铺预期利润总价。即当前商铺卖完能赚多少钱。
     * 利润百分百由用户输入。
     * 不等同于已获得利润。  已获得利润以出库记录计算
     * 首先获取goodsInfo表中当前商品含有的商品的信息，
     * 如果数量不为0，通过商品id去出入库表找最新的一条记录的单价。最新单价*利润*数量
     * 最后将现有商品的利润总加加起来就获得当前商铺的预期利润总价了
     */
    public int getAllTradeProfitTotal(String userId) {
        List<Goodsinfo> goodsinfoList = goodsinfoMapper.getAllTrade(userId);
        int profitTotal = 0;
        for (Goodsinfo g : goodsinfoList) {
            Goods goods = goodsMapper.selectOneById(g.getGoodsInfoId(), userId);
            if (g.getNumber() > 0) {
               if(goods!=null){
                profitTotal = profitTotal + goods.getPrice() * g.getNumber() * g.getProfit() / 100;
                 }
            }
        }
        return profitTotal;
    }

    /**
     * Tips·
     * 获取当前商铺已赚到金额。即当前商铺通过商品出入库获得的金额
     * 利润百分百由用户输入。
     * 单个商品的利润是商品出库的数量*（商品的出库金额*预设的利润百分百）
     * 将单个商品的利润加起来即可
     */
    public int getTradeGetProfitTotal(String userId) {
        List<Goodsinfo> goodsinfoList = goodsinfoMapper.getAllTrade(userId);  //获得所有商品信息
        int Total = 0;
        for (Goodsinfo g : goodsinfoList) {
            Object sum = goodsMapper.selectOutGoodsById(g.getGoodsInfoId(), userId);
            if (sum != null) {
                int b = Integer.parseInt(sum.toString());
                Total = Total + b * g.getProfit() / 100;
            }
        }
        return Total;
    }

    /**
     * 查询所有客户总数
     *
     * @param userId
     * @return
     */
    public int getAllwholesalerNumber(String userId) {
        Object ob = wholesalerMapper.getAllwholesaler(userId);
        int b = 0;
        if (ob != null) {
            b = Integer.parseInt(ob.toString());
        }
        return b;
    }
    /**
     * 获取某年季度的营业额
     * createTime是字符串，用like  %2019-01  数据库用sum获取insum的总额。这样获取每个月的总额
     */
    @Override
    public List<Map> getQuarterInfo(String userId,String year) {
        String month1=year+"-01";  String month2=year+"-02";String month3=year+"-03";String month4=year+"-04";
        String month5=year+"-05";String month6=year+"-06";String month7=year+"-07";String month8=year+"-08";
        String month9=year+"-09";String month10=year+"-10";String month11=year+"-11";String month12=year+"-12";
        /**这是一段丑陋的代码*/
        //1月
        int Jua=0;
        Object ob1=budgetMapper.getOneMonthAllSum(userId,month1);
        if(ob1!=null){ Jua = Integer.parseInt(ob1.toString());}
        //2月
        int Feb=0;
        Object ob2=budgetMapper.getOneMonthAllSum(userId,month2);
        if(ob2!=null){ Feb = Integer.parseInt(ob2.toString());}
        //3月
        int Mar=0;
        Object ob3=budgetMapper.getOneMonthAllSum(userId,month3);
        if(ob3!=null){ Mar = Integer.parseInt(ob3.toString());}
        //4月
        int Apri=0;
        Object ob4=budgetMapper.getOneMonthAllSum(userId,month4);
        if(ob4!=null){ Apri = Integer.parseInt(ob4.toString());}
        //5月
        int May=0;
        Object ob5=budgetMapper.getOneMonthAllSum(userId,month5);
        if(ob5!=null){ May = Integer.parseInt(ob5.toString());}
        //6月
        int June=0;
        Object ob6=budgetMapper.getOneMonthAllSum(userId,month6);
        if(ob6!=null){ June = Integer.parseInt(ob6.toString());}
        //7月
        int July=0;
        Object ob7=budgetMapper.getOneMonthAllSum(userId,month7);
        if(ob7!=null){ June = Integer.parseInt(ob7.toString());}
        //8月
        int Aug=0;
        Object ob8=budgetMapper.getOneMonthAllSum(userId,month8);
        if(ob8!=null){ Aug = Integer.parseInt(ob8.toString());}
        //9月
        int Sept=0;
        Object ob9=budgetMapper.getOneMonthAllSum(userId,month9);
        if(ob9!=null){ Sept = Integer.parseInt(ob9.toString());}
        //10月
        int Oct=0;
        Object ob10=budgetMapper.getOneMonthAllSum(userId,month10);
        if(ob10!=null){ Oct = Integer.parseInt(ob10.toString());}
        //11月
        int Nove=0;
        Object ob11=budgetMapper.getOneMonthAllSum(userId,month11);
        if(ob11!=null){ Nove = Integer.parseInt(ob11.toString());}
        //12月
        int Dece=0;
        Object ob12=budgetMapper.getOneMonthAllSum(userId,month12);
        if(ob12!=null){ Dece = Integer.parseInt(ob12.toString());}
Integer firstQuarter=Jua+Feb+Mar;
        Integer secondQuarter=Apri+May+June;
        Integer thirdQuarter=July+Aug+Sept;
        Integer fourthQuarter=Oct+Nove+Dece;
        int[] sumList={firstQuarter,secondQuarter,thirdQuarter,fourthQuarter};
        List<Map> resultList=new ArrayList<>();

        for (int i=0;i<4;i++){
            Map map=new HashMap();
            map.put("季度","第"+(i+1)+"季度");
map.put("该季度营业额",sumList[i]);
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map> getOneMonthComeAndOut(String userId, String date) {
        List<Map> list=budgetMapper.getOneMonthComeAndOut(userId,date);          //通过对月份进行模糊查询，查询到该月份的所有记录信息，返回一个list
        List<Map> resultList=new ArrayList<>();                       //以下是将数据整理成为符合v-charts的格式
        for(int i=0;i<list.size();i++){
            Map m=new HashMap();
            Object createTime = list.get(i).get("createTime");
            String time=String.valueOf(createTime);

            Object allInSum = list.get(i).get("AllInSum");
            Integer inSum = Integer.parseInt(allInSum.toString());

            Object allOutSum = list.get(i).get("AllOutSum");
            Integer outSum = Integer.parseInt(allOutSum.toString());
            m.put("日期",time.substring(5,10));
            m.put("当天收入",inSum);
            m.put("当天支出",outSum);
            resultList.add(m);
        }
        return resultList;
    }


}
