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
    public List<Budget> selectByUserId(String userId) {
        return budgetMapper.selectByUserId(userId);
    }

    @Override
    public List<Budget> selectByUserIdLimit7(String userId) {
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
        if (budget.getBudgetId().isEmpty()) {
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
                Total = Total + goods.getPrice() * g.getNumber();
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
            if (g.getNumber() > 0) {
                Goods goods = goodsMapper.selectOneById(g.getGoodsInfoId(), userId);
                profitTotal = profitTotal + goods.getPrice() * g.getNumber() * g.getProfit() / 100;
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

}
