package com.sise.shop.mapper;

import com.sise.shop.entity.Wholesaler;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 批发商表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-14
 */
public interface WholesalerMapper extends BaseMapper<Wholesaler> {
    /**
     * 查询所有客户的数量
     * @param userId
     * @return
     */
    @Select("SELECT count(wholesalerId) from wholesaler WHERE userId=#{userId} ")
    Object getAllwholesaler(@Param("userId")String userId);

    @Select("SELECT count(wholesalerId) from wholesaler WHERE  userId=#{userId}")
    Object getAllWholesalerNumber(@Param("userId")String userId);

    @Select("SELECT count(wholesalerId) from wholesaler WHERE  userId=#{userId} AND status='customer'")
    Object getCustmerNumber(@Param("userId")String userId);

    @Select("SELECT count(wholesalerId) from wholesaler WHERE  userId=#{userId} AND status='wholesaler'")
    Object getWholesalerNumber(@Param("userId")String userId);

    @Select("SELECT * from wholesaler WHERE  userId=#{userId} AND status='wholesaler'")
    List<Map> getwholesalerList(@Param("userId")String userId);
}
