package com.sise.shop.controller;




import com.sise.shop.entity.User;
import com.sise.shop.entity.User_info;
import com.sise.shop.service.IUser_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
public class userController {

    @Autowired
    private IUser_infoService iUser_infoService;

  @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String helloHtml(HashMap<String, Object> map, Model model) {
      User_info user_info = iUser_infoService.selectById1("12345");
      System.out.println(user_info);
        return "login";
    }

    @ResponseBody
    @RequestMapping(value="/logincheck", method=RequestMethod.POST)
    public User test(User user){
      System.out.println(user.getUsername());
      return user;
    }

 }
