package com.sise.shop.utilis;

import org.apache.commons.collections4.MapUtils;
import org.thymeleaf.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
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
 * 将时间格式转换成字符串
 */
public static String dateTostring(Date data){
    String fmt = "yyyy-MM-dd hh:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    String dateStr = sdf.format(data);
    return dateStr;
}
    /**
     * 获取UUid的值
     *
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取map中的UserId
     *
     * @param map
     * @return
     */
    public static String getUserId(Map map) {
        String userId = MapUtils.getString(map, "userId");
        if (!StringUtils.isEmpty(userId)) {
            return userId;
        }
        return null;
    }

    /**
     * 传入一个时间字符串+3位生成随机编号
     */
    public static String getOrderNumber(String date) {
        String data = date.replaceAll("-", "");
        String[] split = data.split(" ");
        String orderNumberDate = split[0];
        String random = String.valueOf((int) ((Math.random() * 9 + 1) * 100));
        String orderNumber = orderNumberDate + random;
        return orderNumber;
    }
}
