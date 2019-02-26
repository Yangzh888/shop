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
import java.util.*;

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
            String a=shopUtils.dateTostring(new Date());
            wholesaler.setCreateTime(shopUtils.dateTostring(new Date()));
            Integer insert = wholesalerMapper.insert(wholesaler);
            if(insert<0)return false;
        }else{
            Integer integer = wholesalerMapper.updateAllColumnById(wholesaler);
            if(integer<0)return false;
        }
        return true;
    }

    @Override
    public Integer getAllWholesalerNumber(String userId) {
        Object ob=wholesalerMapper.getAllWholesalerNumber(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }

    @Override
    public Integer getCustmerNumber(String userId) {
        Object ob=wholesalerMapper.getCustmerNumber(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }

    @Override
    public Integer getWholesalerNumber(String userId) {
        Object ob=wholesalerMapper.getWholesalerNumber(userId);
        int b=0;
        if(ob!=null){ b = Integer.parseInt(ob.toString());}
        return b;
    }

    @Override
    public List<Map> getWholesalerInfoList(String userId) {
        String monthList[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};//关于月份标签存放
        int January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0, September = 0, October = 0, November = 0, December = 0;
        List<Map> wholesalerList = wholesalerMapper.getwholesalerList(userId);
        for (Map m : wholesalerList) {
            String createTime = MapUtils.getString(m, "createTime");
            String month = createTime.substring(0, 7);
            switch (month) {
                case "2019-01":
                    January = January + 1;
                    break;
                case "2019-02":
                    February = February + 1;
                    break;
                case "2019-03":
                    March = March + 1;
                    break;
                case "2019-04":
                    April = April + 1;
                    break;
                case "2019-05":
                    May = May + 1;
                    break;
                case "2019-06":
                    June = June + 1;
                    break;
                case "2019-07":
                    July = July + 1;
                    break;
                case "2019-08":
                    August = August + 1;
                    break;
                case "2019-09":
                    September = September + 1;
                    break;
                case "2019-10":
                    October = October + 1;
                    break;
                case "2019-11":
                    November = November + 1;
                    break;
                case "2019-12":
                    December = December + 1;
                    break;
            }
        }
         int[] monthValue={January,February,March,April,May,June,July,August,September,October,November,December};
        List<Map> resultList=new ArrayList<>();
        for(int i=0;i<12;i++){
            Map m=new HashMap();
            m.put("月份",monthList[i]);
            m.put("新增数量",monthValue[i]);
            resultList.add(m);
        }
        return resultList;
    }
}
