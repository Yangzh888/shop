package com.sise.shop.service.impl;

import com.sise.shop.entity.Relationuserinfo;
import com.sise.shop.mapper.RelationuserinfoMapper;
import com.sise.shop.service.IRelationuserinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户子表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-03-17
 */
@Service
public class RelationuserinfoServiceImpl extends ServiceImpl<RelationuserinfoMapper, Relationuserinfo> implements IRelationuserinfoService {
@Resource
private  RelationuserinfoMapper relationuserinfoMapper;


    @Override
    public Result saveUserSonInfo( Map userSonInfoMap) throws InvocationTargetException, IllegalAccessException {
        Relationuserinfo relationuserinfo=new Relationuserinfo();
        BeanUtils.populate(relationuserinfo,userSonInfoMap);
        String relationUserInfoName = MapUtils.getString(userSonInfoMap, "relationUserInfoName");
        String relationUserInfoId = MapUtils.getString(userSonInfoMap, "relationUserInfoId");
        if(relationuserinfo.getPermissionlevel()==null){                     //先将permissionlevel定义为ID....，如果是空的，则为新建，不为空的则为新增
            String userId = shopUtils.getUserId(userSonInfoMap);
            String userSonId = shopUtils.getUserSonId(userSonInfoMap,"relationUserInfoId");
            List<Relationuserinfo> relationuserinfos = relationuserinfoMapper.checkIsExist(userSonId);        //校验新注册的账号是否已经存在
            if(relationuserinfos.size()>0||!relationuserinfos.isEmpty()){
                return ResultFactory.buildFailResult("当前账号已被使用，请修改后重新尝试");
            }
            relationuserinfo.setPermissionlevel(shopUtils.getUuid());
            relationuserinfo.setUserId(userId);              //将关联得父用户设置进去
            relationuserinfo.setRelationUserInfoName(relationUserInfoName);
            relationuserinfo.setRelationUserInfoId(relationUserInfoId);
            //Integer insert = relationuserinfoMapper.insert(relationuserinfo);
            boolean insert1 = relationuserinfo.insert();
            if (insert1) {
                return ResultFactory.buildSuccessResult("新增成功");
            } else {
                return ResultFactory.buildFailResult("执行失败，请稍后重试");
            }
        }else{              //因为密码是经过加密的，所有需要首先获得原来的密码，在把原来的密码set进实体类进行更新数据。
            String userSonId = relationuserinfo.getRelationUserInfoId();
            String password = relationuserinfoMapper.selectById(userSonId).getPassword();//原来的加密后的密码
            relationuserinfo.setPassword(password);
            Integer integer = relationuserinfoMapper.updateById(relationuserinfo);
            if (integer > 0) {
                return ResultFactory.buildSuccessResult("新增成功");
            } else {
                return ResultFactory.buildFailResult("执行失败，请稍后重试");
            }
        }


        }

    @Override
    public Integer updateUserSonPassword(String userSonId, String password) {
        Integer b=  relationuserinfoMapper.updateUserSonPassword(userSonId,password);
        return b;
    }

    @Override
    public boolean insertUserSonInfo(String userId, String relationUserInfoName, String password, String createTime) {
        Relationuserinfo r=new Relationuserinfo();
        r.setUserId(userId);
        r.setRelationUserInfoId(userId);
        r.setPassword(password);
        r.setRelationUserInfoName(relationUserInfoName);
        r.setCreateTime(createTime);
        r.setLevel(1);
        r.setReadyDolevel("true");
        r.setGoodslevel("true");
        r.setComeAndOutlevel("true");
        r.setAnaylyslevel("true");
        r.setCustomerlevel("true");
        Integer insert = relationuserinfoMapper.insert(r);
        return insert>0?true:false;
    }
}
