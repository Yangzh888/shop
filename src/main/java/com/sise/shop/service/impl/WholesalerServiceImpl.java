package com.sise.shop.service.impl;

import com.sise.shop.entity.Wholesaler;
import com.sise.shop.mapper.WholesalerMapper;
import com.sise.shop.service.IWholesalerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 批发商表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-14
 */
@Service
public class WholesalerServiceImpl extends ServiceImpl<WholesalerMapper, Wholesaler> implements IWholesalerService {
@Resource
private WholesalerMapper wholesalerMapper;

    @Override
    public boolean saveWholesaler(Map map) throws InvocationTargetException, IllegalAccessException {
        Wholesaler wholesaler=new Wholesaler();
        Map wholesalerInfo  = MapUtils.getMap(map, "form");
        BeanUtils.populate(wholesaler,wholesalerInfo);
        if(MapUtils.getString(wholesalerInfo,"wholesalerId").isEmpty()){
            wholesaler.setUserId(shopUtils.getUserId(map));  //将关联属性set进去
            wholesaler.setWholesalerId(shopUtils.getUuid());
            String a=shopUtils.dataToString(new Date());
            wholesaler.setCreateTime(shopUtils.dataToString(new Date()));
            Integer insert = wholesalerMapper.insert(wholesaler);
            if(insert<0)return false;
        }else{
            Integer integer = wholesalerMapper.updateAllColumnById(wholesaler);
            if(integer<0)return false;
        }
        return true;
    }
}
