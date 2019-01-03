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

    @Select("select  * from userInfo where userId=#{userId}")
    List<Userinfo>  checkUnqiue(@Param("userId")String userId) ;


    @Select("select * from userInfo where userId =#{userId} and password=#{password}")
    List<Userinfo> checekLogin(@Param("userId")String userId, @Param("password")String password);


    @Insert("insert into users(name,age) values(#{name},#{age})")
    public void insertT(Userinfo user);

    @Delete("delete from users where id=#{id}")
    public void deleteById(int id);

    @Update("update users set name=#{name},age=#{age} where id=#{id}")
    public void updateT(Userinfo user);

    @Select("select * from users where id=#{id}")
    public Userinfo getUser(int id);

    @Select("select * from users")
    public List<Userinfo> getAllUsers();
}
