package com.sise.shop.mapper;

import com.sise.shop.entity.Budget;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-01-03
 */
public interface BudgetMapper extends BaseMapper<Budget> {

    @Select("select * from  budget where userId=#{userId} order by createTime desc")
    List<Budget> selectByUserId(String userId);

    @Select("select  * from budget where userId=#{userId} GROUP BY createTime order by createTime DESC  LIMIT 7")
    List<Budget> selectByUserIdLimit7(String userId);


}
