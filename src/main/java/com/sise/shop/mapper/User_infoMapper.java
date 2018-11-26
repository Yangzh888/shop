package com.sise.shop.mapper;

import com.sise.shop.entity.User_info;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public interface User_infoMapper extends BaseMapper<User_info> {

    public User_info selectById1(String ID);
}