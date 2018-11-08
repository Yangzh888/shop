package com.sise.shop.controller;

import com.sise.shop.entity.User;
import com.sise.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class userController {
@Autowired
private IUserService iUserService;

    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {

        User user =iUserService.selectById("1");
        String s1= user.getPassword();
        map.put("hello", "欢迎进入HTML页面");
        map.put("s1",s1);
        return "/index";
    }

}
