package com.sise.shop.mapper;

import com.sise.shop.entity.Relationuserinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户子表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-03-17
 */
public interface RelationuserinfoMapper extends BaseMapper<Relationuserinfo> {

    @Select("select * from RelationUserInfo where RelationUserInfoId=#{userSonId}")
    List<Relationuserinfo> checkIsExist(@Param("userSonId")String userSonId);

    @Update("update relationuserinfo set password=#{password} where relationUserInfoId=#{userSonId}")
    Integer updateUserSonPassword(@Param("userSonId")String userSonId, @Param("password")String password);
}
