package com.sise.shop.mapper;

import com.sise.shop.entity.Others;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 其他表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface OthersMapper extends BaseMapper<Others> {

    @Select("select * from others where userId=#{userId}")
    List<Others> queryOthersByUserId(String userId);

    @Update("update others set status=#{status},relationUserInfoId=#{relationUserInfoId},relationUserInfoName=#{relationUserInfoName} where othersId=#{othersId}")
    Integer updateStatus(@Param("othersId")String othersId,@Param("status") String status,@Param("relationUserInfoId")String relationUserInfoId,@Param("relationUserInfoName")String relationUserInfoName);

    Integer changeBatchStatus(@Param("status")String status,@Param("list")List list);
}
