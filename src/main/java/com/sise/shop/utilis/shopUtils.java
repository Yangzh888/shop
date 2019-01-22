package com.sise.shop.utilis;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商铺管理系统工具类
 */
public class shopUtils {

    /**
     * 将string格式的日期转换成DATA类型
     * @param source
     * @return
     */
            public static Date formatData(String source) throws ParseException {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date =  formatter.parse(source);
                return date;
            }



}
