package com.sise.shop.mapper;

import com.sise.shop.entity.Goods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 获取该商品最近的一次进货记录---获取商品最新价格
     * @param goodsInfoId
     * @param userId
     * @return
     */
    @Select("SELECT * from goods WHERE userId=#{userId} AND status='come' AND goodsInfoId=#{goodsInfoId} ORDER BY createTime DESC LIMIT 1")
    Goods selectOneById(@Param("goodsInfoId")String goodsInfoId,@Param("userId")String userId);

    /**
     * 通过商品ID获取商品出库记录的总金额
     * @param goodsInfoId
     * @param userId
     * @return
     */
    @Select("SELECT sum(sum) from goods WHERE userId=#{userId} AND status='out' AND goodsInfoId=#{goodsInfoId} ")
    Object selectOutGoodsById(@Param("goodsInfoId")String goodsInfoId, @Param("userId")String userId);


    /**
     * 查询所有订单的总数量
     * @param userId
     * @return
     */
    @Select("SELECT count(goodsId) from goods WHERE userId=#{userId} ")
    Object getGoodsNumber(@Param("userId")String userId);

    /**
     * 查询所有出库订单的总数量
     * @param userId
     * @return
     */
    @Select("SELECT count(goodsId) from goods WHERE userId=#{userId} AND status='out'")
    Object getGoodsOutNumber(@Param("userId")String userId);

    /**
     * 查询所有出库订单的总金额
     * @param userId
     * @return
     */
    @Select("SELECT sum(sum) from goods WHERE userId=#{userId} AND status='out'")
    Object getGoodstOutTotal(@Param("userId")String userId);

    /**
     * 查询所有出库订单
     * @param userId
     * @return
    */
    @Select("SELECT * from goods WHERE userId=#{userId} AND status='out'")
    List<Map> getBudgetOutOrder(String userId);
}
