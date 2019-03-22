package com.sise.shop.utilis;

import com.sise.shop.utilis.result.Result;
import com.sise.shop.utilis.result.ResultFactory;
import org.apache.commons.collections4.MapUtils;
import org.thymeleaf.util.StringUtils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商铺管理系统工具类
 */
public class shopUtils {

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
    public static String getUserSonId(Map map,String key) {
        String userId = MapUtils.getString(map, key);
        if (!StringUtils.isEmpty(userId)) {
            return userId;
        }
        return null;
    }

    /**
     * 传入一个时间字符串+5位生成随机编号
     */
    public static String getOrderNumber(String date) {
        String substring = date.substring(0, 10);          //获取标准值  yyyy:mm:dd
        String date1 = substring.replaceAll("-", "");
        String[] split = date1.split(" ");
        String orderNumberDate = split[0];
        String random = String.valueOf((int) ((Math.random() * 9 + 1) * 10000));
        String orderNumber = orderNumberDate + random;
        return orderNumber;
    }
    /**
    *传入一个带分页数据的Map，返回一个Integer的current
     */
    public static Integer getCurrentByMap(Map  map) {
        Integer current;
        if(MapUtils.getInteger(map,"current")!=null){
            current=MapUtils.getInteger(map,"current");
        }else
            current=1;
        return current;
    }

    /**
     * 输入一个月份，获得这个月的天数的值，返回一个Map，key是label，value是日期---用于返回图表数据
     * @param integer
     * @return
     */
    public  static Map getDaysByMonth(Integer integer,String label){
        Map result=new HashMap();
        if(integer==1||integer==3||integer==5||integer==7||integer==8||integer==10||integer==12){
        for(int i=1;i<=31;i++){
            result.put(label,i);
        }}
        else if(integer==4||integer==6||integer==9||integer==11){
            for(int i=1;i<=30;i++){
                result.put(label,i);
        }}else {
            if(integer!=2){
                return result;
            }
           result.put(label,28);
        }return result;
    }
    /**移除Map中值为空的键值对*/
     public static void removeNullEntry(Map map) {
                removeNullKey(map);
                 removeNullValue(map);
            }
      /**移除键为空的键值对*/
             public static void removeNullKey(Map map) {
                Set set = map.keySet();
               for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                       Object obj = (Object) iterator.next();
                       remove(obj, iterator);
                  }
           }
     /**移除值为空的键值对*/
            public static void removeNullValue(Map map) {
                Set set = map.keySet();
               for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
                        Object obj = (Object) iterator.next();
                       Object value = (Object) map.get(obj);
                       remove(value, iterator);
                   }
            }

            private static void remove(Object obj, Iterator iterator) {
                 if (obj instanceof String) {
                       String str = (String) obj;
                       if (str == null || str.trim().isEmpty()) {
                               iterator.remove();
                          }
                  } else if (obj instanceof Collection) {
                       Collection col = (Collection) obj;
                       if (col == null || col.isEmpty()) {
                              iterator.remove();
                           }

                  } else if (obj instanceof Map) {
                       Map temp = (Map) obj;
                       if (temp == null || temp.isEmpty()) {
                                 iterator.remove();
                            }

                   } else if (obj instanceof Object[]) {
                        Object[] array = (Object[]) obj;
                        if (array == null || array.length <= 0) {
                                 iterator.remove();
                            }
                    } else {
                        if (obj == null) {
                               iterator.remove();
                            }
                  }
           }
    /**
     * 判断是否操作成功，传入一个Integer，大于0则操作成功，否则操作失败
     */
public static Result isSuccess(Integer integer,String successSting,String falseString){
    if(integer>0){

        return ResultFactory.buildSuccessResult(successSting);
    }else{
        return ResultFactory.buildFailResult(falseString);
    }
}
    /**
     * 判断是否操作成功，传入一个boolean，成功返回成功对象，失败返回失败对象
     */
    public static Result booleanIsSuccess (Boolean b,String successSting,String falseString){
        if(b){
            return ResultFactory.buildSuccessResult(successSting);
        }else{
            return ResultFactory.buildFailResult(falseString);
        }
    }
}
