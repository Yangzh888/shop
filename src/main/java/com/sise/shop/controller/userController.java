package com.sise.shop.controller;



import com.sise.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class userController {
@Autowired
private IUserService iUserService;

 /*   @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String helloHtml(HashMap<String, Object> map,Model model) {
        User user =iUserService.selectById("1");
        map.put("hello", "欢迎进入HTML页面");
        System.out.println(user);
        return "login";
    }
    @ResponseBody
    @RequestMapping(value="/logincheck", method=RequestMethod.POST)
    public String test(User user){
        String uname=user.getUsername();
        String pwd=user.getPassword();
        System.out.println(user);
        System.out.println(pwd);
        return "index";
    }*/

 }
