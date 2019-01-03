package com.sise.shop.service.impl;

import com.sise.shop.entity.Userinfo;
import com.sise.shop.mapper.UserinfoMapper;
import com.sise.shop.service.IUserinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    public List<Userinfo> login(String userId, String password) {
        List<Userinfo> list = userinfoMapper.checekLogin(userId, password);

        if (list == null && list.isEmpty()) {
            return null;
        } else {
            return list;
        }

    }

    @Override
    public List<Userinfo> checkUnqiue(String userId) {
        List<Userinfo> list=  userinfoMapper.checkUnqiue(userId);
        if (list == null && list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
