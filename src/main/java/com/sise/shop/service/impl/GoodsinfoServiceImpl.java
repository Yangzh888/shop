package com.sise.shop.service.impl;

import com.sise.shop.entity.Goodsinfo;
import com.sise.shop.mapper.GoodsinfoMapper;
import com.sise.shop.service.IGoodsinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-13
 */
@Service
public class GoodsinfoServiceImpl extends ServiceImpl<GoodsinfoMapper, Goodsinfo> implements IGoodsinfoService {

    @Resource
    private GoodsinfoMapper goodsinfoMapper;
    @Override
    public boolean saveGoodsInfo(Map map) throws InvocationTargetException, IllegalAccessException {
        Goodsinfo goodsinfo=new Goodsinfo();
        Map form = MapUtils.getMap(map, "form");
        String userId = shopUtils.getUserId(map);
        BeanUtils.populate(goodsinfo,form);
        if(goodsinfo.getUserId().isEmpty()){
            goodsinfo.setNumber(0);
            goodsinfo.setUserId(userId);
            goodsinfo.setGoodsInfoId(shopUtils.getUuid());
            goodsinfoMapper.insert(goodsinfo);
        }else {
            goodsinfoMapper.updateById(goodsinfo);
        }
        return true;
    }
}
