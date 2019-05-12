package com.sise.shop.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import com.sise.shop.entity.Others;
import com.sise.shop.service.IOthersService;
import com.sise.shop.utilis.MapRequestVO;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
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
     * 简单保存代办信息
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/saveReadyDo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result saveReadyDo(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {

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
        Integer current = shopUtils.getCurrentByMap(map);
        Page<Others> page = new Page<Others>();
        EntityWrapper<Others> eWrapper = new EntityWrapper<Others>(others);
        eWrapper.eq("userId",shopUtils.getUserId(map));
        eWrapper.eq("status",MapUtils.getString(map,"status"));               //通过前段传来的map判断要查询什么状态下的待办信息
        String selectWord = MapUtils.getString(map, "selectWord");
        if(!StringUtils.isEmpty(selectWord))
        {
            eWrapper.like("title",selectWord,SqlLike.DEFAULT);
        }
        Page<Others> othersList = others.selectPage(page,eWrapper);
        return  othersList;
    }


    /**
     * 查询待办分页数据
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/selectPage1", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Page<Others> selectPage1(@RequestBody Map map){
        Others others = new Others();
        Integer current = shopUtils.getCurrentByMap(map);
        Page<Others> page = new Page<Others>(current,3);
        EntityWrapper<Others> eWrapper = new EntityWrapper<Others>(others);
        eWrapper.eq("userId",shopUtils.getUserId(map));
        eWrapper.eq("status",MapUtils.getString(map,"status"));               //通过前段传来的map判断要查询什么状态下的待办信息
        String selectWord = MapUtils.getString(map, "selectWord");
        if(!StringUtils.isEmpty(selectWord))
        {
            eWrapper.like("title",selectWord,SqlLike.DEFAULT);
        }
        Page<Others> othersList = others.selectPage(page,eWrapper);
        return  othersList;
    }


    /**
     * 根据OthersID更新状态
     * @param map
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStatus(@RequestBody Map map){
        Result result=iOthersService.changeStatus(map);
      return result;
    }

    /**
    * 批量通过othersIdList更新状态
    */
    @CrossOrigin
    @RequestMapping(value = "/changeBatchStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeBatchStatus(@RequestBody Map map){
        Result result=iOthersService.changeBatchStatus(map);
        return result;
    }
}
