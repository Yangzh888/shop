package com.sise.shop.controller;


import com.sise.shop.entity.Others;
import com.sise.shop.service.IOthersService;
import com.sise.shop.utilis.MapRequestVO;
import com.sise.shop.utilis.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 其他表 前端控制器
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Controller
@RequestMapping("/api/others")
public class OthersController {
    @Autowired
    private IOthersService iOthersService;

    /**
     * 通过传来的用户ID，查询用户的待办信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getOthersDate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Others> getOthersDate(@RequestBody Map map){
        String userId = (String) map.get("userId");
        List<Others> list=iOthersService.queryOthersByUserId(userId);
        return list;
    }

    /**
     * 保存代办信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/saveReadyDo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveReadyDo(@RequestBody Map map){

        Result result= iOthersService.saveReadyDo(map);

        return result;
    }
}
