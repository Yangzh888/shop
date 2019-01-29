package com.sise.shop.utilis;

import org.apache.commons.collections4.MapUtils;
import org.thymeleaf.util.StringUtils;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * 商铺管理系统工具类
 */
public class shopUtils {

    /**
     * 将string格式的日期转换成DATA类型
     *
     * @param source
     * @return
     */
    public static Date formatData(String source) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(source);
        return date;
    }

    /**
     * 获取UUid的值
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取map中的UserId
     * @param map
     * @return
     */
    public static String getUserId(Map map){
        String userId = MapUtils.getString(map, "userId");
        if(StringUtils.isEmpty(userId)){
            return userId ;
        }
        return null;
    }
}
