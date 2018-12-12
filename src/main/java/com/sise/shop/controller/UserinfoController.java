package com.sise.shop.controller;


import com.sise.shop.entity.Userinfo;
import com.sise.shop.service.IUserinfoService;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping()
public class UserinfoController {

    @Autowired
    private IUserinfoService iUserinfoService;

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
    @CrossOrigin
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@Valid @RequestBody Userinfo userInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        if (!userInfo.getUserId().isEmpty()) {
            String password = userInfo.getPassword();
            Userinfo user = iUserinfoService.selectById(userInfo.getUserId());
            if (!password.equals(user.getPassword())) {
                String message = String.format("登陆失败，详细信息[用户名或密码信息不正确]。");
                return ResultFactory.buildFailResult(message);
            } else {
                return ResultFactory.buildSuccessResult("登陆成功。");

            }
        }
        String message = String.format("登陆异常，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
        return ResultFactory.buildFailResult(message);
    }
}
