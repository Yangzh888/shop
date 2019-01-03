package com.sise.shop.service;

import com.sise.shop.entity.Userinfo;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface IUserinfoService extends IService<Userinfo> {

    /**
     * 登录校验
     */
    List<Userinfo>  login(String userId,String password);

    /**
     * 校验主键唯一性
     * @param userId
     * @return
     */
    List<Userinfo> checkUnqiue(String userId);
}
