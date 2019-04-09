package com.sise.shop.service;

import com.sise.shop.entity.Relationuserinfo;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户子表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-03-17
 */
public interface IRelationuserinfoService extends IService<Relationuserinfo> {
    /**
     * 保存子ID的信息
     * @param userSonInfoMap  子信息
     * @return
     */

    Result saveUserSonInfo(Map userSonInfoMap) throws InvocationTargetException, IllegalAccessException;

    /**
     * 管理员更新密码
     * @param userSonId
     * @param password
     * @return
     */
    Integer updateUserSonPassword(String userSonId, String password);

    /**
     * 注册新店铺时管理员同时更新子表数据
     */
    boolean insertUserSonInfo(String userId,String relationUserInfoName,String password,String createTime);


    /**
     * 超级管理员
     * 获取所有人用户的信息
     * @return
     */
    List<Map> getAllUserInfo();



}
