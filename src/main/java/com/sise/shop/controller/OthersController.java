package com.sise.shop.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import com.sise.shop.entity.Others;
import com.sise.shop.service.IOthersService;
import com.sise.shop.utilis.MapRequestVO;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.apache.commons.collections4.MapUtils;
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
    /**
     * 删除代办信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/deleteReadyDo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteReadyDo(@RequestBody Map map){
        String othersId = MapUtils.getString(map, "othersId");
        boolean deleteById = iOthersService.deleteById(othersId);
        Result result= ResultFactory.buildSuccessResult(deleteById);
        return result;
    }

    /**
     * 查询待办分页数据
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Others> selectPage(@RequestBody Map map){
        Others others = new Others();
        String userId = (String) map.get("userId");
        others.setUserId(userId);
        Page<Others> page = new Page<Others>();
        EntityWrapper<Others> eWrapper = new EntityWrapper<Others>(others);
        Page<Others> othersList = others.selectPage(page,eWrapper);
        return  othersList;
    }
}
