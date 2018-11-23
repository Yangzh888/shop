package com.sise.shop.service;

import com.sise.shop.entity.User_info;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public interface IUser_infoService extends IService<User_info> {

     User_info selectById1(String ID);
}
