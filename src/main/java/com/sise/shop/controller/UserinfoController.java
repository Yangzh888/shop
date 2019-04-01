package com.sise.shop.controller;


import com.sise.shop.entity.Relationuserinfo;
import com.sise.shop.entity.Userinfo;
import com.sise.shop.service.IRelationuserinfoService;
import com.sise.shop.service.IUserinfoService;
import com.sise.shop.utilis.MapRequestVO;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping("/api/userInfo")
public class UserinfoController {


    @Autowired
    private IUserinfoService iUserinfoService;
    @Autowired
    private IRelationuserinfoService iRelationuserinfoService;
    /**
     * 测试
     */
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "index";
    }

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
    @CrossOrigin
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login( @RequestBody Userinfo userInfo, BindingResult bindingResult) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if (bindingResult.hasErrors()) {
            return ResultFactory.buildFailResult("登陆失败，详细信息[%s]。");
        }
        if (!userInfo.getUserId().isEmpty()) {
            String password = userInfo.getPassword();
            String userId=userInfo.getUserId();
            List<Userinfo> login = iUserinfoService.login(password, userId);
            if (login==null||login.isEmpty()||login.size()==0) {
                return ResultFactory.buildFailResult("登陆失败，详细信息[用户名或密码信息不正确]。");
            } else {                                                                                       //登录成功
                Relationuserinfo relationuserinfo = iRelationuserinfoService.selectById(userId);
                Userinfo userinfo = iUserinfoService.selectById(relationuserinfo.getUserId());
                Map resultMap=BeanUtils.describe(userinfo);
                resultMap.put("userSonInfo",relationuserinfo);
                shopUtils.removeNullEntry(resultMap);//移除键为空的数据
                resultMap.remove("password");
                return ResultFactory.buildSuccessResult(resultMap);
            }
        }
        return ResultFactory.buildFailResult("登陆异常，详细信息。");
    }

    /**
     * 注册功能
     * @param userInfo
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result register( @RequestBody Userinfo userInfo){
        String userId = userInfo.getUserId();
        //判断当前传过来的账户有没有被注册过(主键唯一性)
        List<Userinfo> userInfoList = iUserinfoService.checkUnqiue(userId);
        if(userInfoList.size()>0){                                                                              //校验用户是否已经存在
          return ResultFactory.buildFailResult("注册失败，当前登录账户已被使用，请使用其他账户");
        }
        boolean insert = iUserinfoService.insert(userInfo);                                                      //主表存放管理员信息
        boolean b = iRelationuserinfoService.insertUserSonInfo(userInfo.getUserId(), userInfo.getUsername(),     //子表将管理员也存放登录信息
                userInfo.getPassword(),shopUtils.dateTostring(new Date()));
        if(insert&&b){
            return ResultFactory.buildSuccessResult("注册成功");
        }else
        {
            return ResultFactory.buildFailResult("注册失败，请重新输入");
        }

    }

    /**
     * 获取当前登录人的信息
     * @param userinfo
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result  getUserInfo( @RequestBody Userinfo userinfo){
        String userId = userinfo.getUserId();
        Userinfo user = iUserinfoService.selectById(userId);
        user.setForgetAns("");
        user.setPassword("");
        return ResultFactory.buildSuccessResult(user);
    }
    /**
     * 更新当前登录人的信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result  updateUserInfo( @RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        Userinfo u=new Userinfo();
        Map userInfoMap= (Map) map.get("form");
        BeanUtils.populate(u,userInfoMap);
        Userinfo userinfo = iUserinfoService.selectById(u.getUserId());
        u.setPassword(userinfo.getPassword());
        u.setForgetAns(userinfo.getForgetAns());
        boolean update = iUserinfoService.updateById(u);
        if(update==true){
            return ResultFactory.buildSuccessResult("更新成功");
        }else
        return ResultFactory.buildFailResult("更新失败");
    }

    /**
     * 获取忘记密码的问题
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getAns", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result  getAns( @RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        Userinfo userinfo = iUserinfoService.selectById(userId);
        String forgetAns = userinfo.getForgetAns();
        return ResultFactory.buildSuccessResult(forgetAns);
    }

    /**
     * 根据问题修改密码
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/checkAnsToChangePassWord", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result  checkAnsToChangePassWord( @RequestBody Map map) {
       boolean b=iUserinfoService.checkAnsToChangePassWord(map);
       if(b==true){
           return ResultFactory.buildSuccessResult(b);
       }else{
           return ResultFactory.buildFailResult("问题答案不正确");
       }

    }


    /**
     * 判断是否存在账号
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/checkIsExitUserId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result  checkIsExitUserId( @RequestBody Map map) {
        String userId = shopUtils.getUserId(map);
        if( iRelationuserinfoService.selectById(userId)!=null){
            Userinfo userinfo = iUserinfoService.selectById(userId);
            return ResultFactory.buildSuccessResult(userinfo);
        }else{
            return ResultFactory.buildFailResult("账号不存在，如有疑问请联系管理员");
        }

    }
}
