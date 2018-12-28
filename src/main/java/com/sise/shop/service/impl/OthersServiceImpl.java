package com.sise.shop.service.impl;

import com.sise.shop.entity.Others;
import com.sise.shop.mapper.OthersMapper;
import com.sise.shop.service.IOthersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
