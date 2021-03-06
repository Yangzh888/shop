package com.sise.shop.mapper;

import com.sise.shop.entity.Userinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    @Select("select  * from relationuserinfo where relationUserInfoId=#{userId}")
    List<Userinfo>  checkUnqiue(@Param("userId")String userId) ;


    @Select("select * from RelationUserInfo where relationUserInfoId =#{userId} and password=#{password}")
    List<Userinfo> checekLogin(@Param("userId")String userId, @Param("password")String password);


    @Select("update relationuserinfo set password=#{newPassWord} where relationUserInfoId=#{userId}")
    void updatePassword(@Param("newPassWord")String newPassWord,@Param("userId")String userId);
}
