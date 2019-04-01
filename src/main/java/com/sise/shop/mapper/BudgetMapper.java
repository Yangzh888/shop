package com.sise.shop.mapper;

import com.sise.shop.entity.Budget;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过createTime分组查询，并统计每天的总额
     * @param userId
     * @return
     */
    @Select("select createTime,sum(inSum) as allInSum,sum(outSum) as allOutSum  from budget where userId=#{userId} GROUP BY createTime order by createTime DESC  LIMIT 7")
    List<Map> selectByUserIdLimit7(String userId);

    @Select("SELECT SUM(inSum) FROM budget where userId=#{userId} and createTime LIKE CONCAT('%',#{month},'%')")
    Object getOneMonthAllSum(@Param("userId")String userId, @Param("month")String month);

    /**
     * 通过createTime分组查询，并统计每天的总额。获得这个月的list总数额
     * @param userId
     * @return
     */
    @Select("SELECT createTime,SUM(inSum) as AllInSum,SUM(outSum) AS AllOutSum FROM budget where userId=#{userId} and " +
            "createTime LIKE CONCAT('%',#{date},'%') GROUP BY createTime")
    List<Map> getOneMonthComeAndOut(@Param("userId")String userId,  @Param("date")String date);
}
