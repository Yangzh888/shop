package com.sise.shop.service.impl;

import com.sise.shop.entity.Others;
import com.sise.shop.mapper.OthersMapper;
import com.sise.shop.service.IOthersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <p>
 * 代办信息表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Service
public class OthersServiceImpl extends ServiceImpl<OthersMapper, Others> implements IOthersService {
    @Resource
     private OthersMapper othersMapper;
    @Override
    public List<Others> queryOthersByUserId(String userId)
    {
        return othersMapper.queryOthersByUserId(userId);
    }

    @Override
    public Result saveReadyDo(Map map) throws InvocationTargetException, IllegalAccessException {
        Map readyDoformMap = (Map) map.get("readyDoform");
        Others others = new Others();
        others.setUserId(shopUtils.getUserId(map));
        String date = (String) readyDoformMap.get("date");
        BeanUtils.populate(others,readyDoformMap);
        others.setOthersId(shopUtils.getUuid());
        others.setCreateTime(date);
        String title = others.getTitle();
        String newTitle="自定义待办:"+title;
        others.setTitle(newTitle);
        Integer insert = othersMapper.insert(others);
        if(insert>0){
            return ResultFactory.buildSuccessResult("保存成功");
        }else{
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    @Override
    public Result changeStatus(Map map) {
        String othersId = MapUtils.getString(map, "othersId");   //获取前端传来唯一ID
        String status = MapUtils.getString(map, "status");
        String relationUserInfoId = MapUtils.getString(map, "relationUserInfoId");
        String relationUserInfoName = MapUtils.getString(map, "relationUserInfoName");
        Integer i=othersMapper.updateStatus(othersId,status,relationUserInfoId,relationUserInfoName);
        return i>0?ResultFactory.buildSuccessResult("更新成功"):ResultFactory.buildFailResult("更新失败");
    }

    @Override
    public Result changeBatchStatus(Map map) {
        List<Map> othersFromList = (List<Map>) map.get("othersFromList");
        String status = MapUtils.getString(map, "status");
        List<String> list=new ArrayList();
        for (Map m:othersFromList){
            String othersId = MapUtils.getString(m, "othersId");
            list.add(othersId);
        }
        Integer integer = othersMapper.changeBatchStatus(status,list);
        return integer>0? ResultFactory.buildSuccessResult("操作成功"+integer+"条数据"):ResultFactory.buildFailResult("操作失败");
    }

    /**
     * 预警信息-新增待办
     */
    public boolean insertReadDo(String userId,String title ,String memo,String creator,String updater){
        String createTime = shopUtils.dateTostring(new Date());
        String uuid = shopUtils.getUuid();
        Others others=new Others();
        others.setTitle(title);
        others.setCreateTime(createTime);
        others.setUserId(userId);
        others.setOthersId(uuid);
        others.setMemo(memo);
        others.setStatus("unread");
        others.setCreator(creator);
        others.setUpdater(updater);
        Integer insert = othersMapper.insert(others);
        return  insert>0?true:false;
    }
}
