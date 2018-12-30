package com.sise.shop.service.impl;

import com.sise.shop.entity.Others;
import com.sise.shop.mapper.OthersMapper;
import com.sise.shop.service.IOthersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 其他表 服务实现类
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
    public Result saveReadyDo(Map map) {
        String userId = (String) map.get("userId");
        Map formLabelAlign = (Map) map.get("formLabelAlign");
        String date = (String) formLabelAlign.get("date");
        String title = (String) formLabelAlign.get("title");
        Others others = new Others();
        //others.setCreateTime(date);
        others.setTitle(title);
        others.setUserId(userId);
        others.setCreateTime(date);
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        others.setOthersId(id);
        othersMapper.insert(others);
        return null;
    }
}
