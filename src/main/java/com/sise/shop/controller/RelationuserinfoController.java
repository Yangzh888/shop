package com.sise.shop.controller;


    import com.baomidou.mybatisplus.enums.SqlLike;
    import com.baomidou.mybatisplus.mapper.EntityWrapper;
    import com.baomidou.mybatisplus.plugins.Page;
    import com.sise.shop.entity.Budget;
    import com.sise.shop.entity.Relationuserinfo;
    import com.sise.shop.service.IRelationuserinfoService;
    import com.sise.shop.utilis.CommonConstant;
    import com.sise.shop.utilis.result.Result;
    import com.sise.shop.utilis.result.ResultFactory;
    import com.sise.shop.utilis.shopUtils;
    import org.apache.commons.collections4.MapUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import org.springframework.stereotype.Controller;
    import org.thymeleaf.util.StringUtils;

    import java.lang.reflect.InvocationTargetException;
    import java.util.List;
    import java.util.Map;

    /**
     * <p>
     * 用户子表 前端控制器
     * </p>
     * 用户子表
     *
     * @author yangzhenhua
     * @since 2019-03-17
     */
    @Controller
    @RequestMapping("/api/relationuserinfo")
    public class RelationuserinfoController {
        @Autowired
        private IRelationuserinfoService iRelationuserinfoService;

        /**
         * 保存子用户信息
         * @param map
         * @return
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        @CrossOrigin
        @RequestMapping(value = "/saveUserSonInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public Result saveUserSonInfo(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {

            Result result=iRelationuserinfoService.saveUserSonInfo(map);
            return result;
        }

        /**
         * 获取子用户信息
         * @param map
         * @return
         */
        @CrossOrigin
        @RequestMapping(value = "/getUserSonInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public List<Relationuserinfo> getUserSonInfo(@RequestBody Map map) {
            Relationuserinfo relationuserinfo=new Relationuserinfo();
            EntityWrapper wrapper=new EntityWrapper(relationuserinfo);
            wrapper.eq("userId",shopUtils.getUserId(map));
            String selectWord = MapUtils.getString(map, "selectWord");              //判断是否有模糊查询参数
            if (!StringUtils.isEmpty(selectWord)) {
                wrapper.like("relationUserInfoName", selectWord, SqlLike.DEFAULT);
                wrapper.or();
                wrapper.like("relationUserInfoId", selectWord, SqlLike.DEFAULT);
            }
            List<Relationuserinfo> resultList = relationuserinfo.selectList(wrapper);
            for (Relationuserinfo u:resultList) {
                u.setPassword("");
            }
            return resultList;
        }

        /**
         * 删除子用户
         * @param map
         * @return
         */
        @CrossOrigin
        @RequestMapping(value = "/deleteUserSonInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public Result deleteUserSonInfo(@RequestBody Map map) {
            String userId = shopUtils.getUserId(map);
            String userSonId = shopUtils.getUserSonId(map,"relationUserInfoId");
            if(userId.equals(userSonId)){
                return ResultFactory.buildFailResult("不能将自己删除");
            }
            boolean b = iRelationuserinfoService.deleteById(userSonId);
            return shopUtils.booleanIsSuccess(b,"删除成功","删除失败，请重试");
        }


        /**
         * 修改子用户密码
         * @param map
         * @return
         */
        @CrossOrigin
        @RequestMapping(value = "/updateUserSonPassword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public Result updateUserSonPassword(@RequestBody Map map) {
            String userId = shopUtils.getUserId(map);
            String userSonId = shopUtils.getUserSonId(map,"relationUserInfoId");
            if(userId.equals(userSonId)){
                return ResultFactory.buildFailResult("管理员修改密码请去个人中心修改");
            }
            String password = MapUtils.getString(map, "password");
            Integer b= iRelationuserinfoService.updateUserSonPassword(userSonId,password);
            return shopUtils.isSuccess(b,"更新成功","更新失败，请重试");
        }
        /**
         * 超级管理员获取所有人信息
         */
        @CrossOrigin
        @RequestMapping(value = "/getAllUserInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public List<Map> getAllUserInfo(){
            List<Map> list = iRelationuserinfoService.getAllUserInfo();
            Relationuserinfo relationuserinfo=new Relationuserinfo();
            EntityWrapper wrapper=new EntityWrapper();
            wrapper.ne("userId","admin");
            List<Relationuserinfo> relationuserinfos = relationuserinfo.selectList(wrapper);
            return  list;
        }
        /**
         * 超级管理员修改密码
         */
        @CrossOrigin
        @RequestMapping(value = "/updatePasswordByAdministrator", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
        @ResponseBody
        public Result updatePasswordByAdministrator(@RequestBody Map  map){
            String password = MapUtils.getString(map, "password");
            String userSonId = shopUtils.getUserSonId(map,"relationUserInfoId");
            Integer b= iRelationuserinfoService.updateUserSonPassword(userSonId,password);
            return shopUtils.isSuccess(b,"更新成功","更新失败，请重试");

        }
    }
