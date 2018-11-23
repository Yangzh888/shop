package com.sise.shop.service.impl;

import com.sise.shop.entity.User_info;
import com.sise.shop.mapper.User_infoMapper;
import com.sise.shop.service.IUser_infoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
@Service
public class User_infoServiceImpl extends ServiceImpl<User_infoMapper, User_info> implements IUser_infoService {
  @Autowired
    private User_infoMapper user_infoMapper;

   public User_info selectById1(String ID){
        return user_infoMapper.selectById1(ID);
    }
}
