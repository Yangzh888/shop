package com.sise.shop.service;

import com.sise.shop.entity.Others;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 其他表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface IOthersService extends IService<Others> {

    public List<Others> queryOthersByUserId(String userId);

    /**
     * 保存代办信息
     * @param formLabelAlign
     * @return
     */
    public  Result saveReadyDo(Map formLabelAlign) throws InvocationTargetException, IllegalAccessException;

    /**
     * 通过待办ID修改待办的状态
     * @param map
     * @return
     */
    Result changeStatus(Map map);

    /**
     * 批量修改待办的状态
     * @param map
     * @return
     */
    Result changeBatchStatus(Map map);

    /**
     *
     * @param userId
     * @param title  待办的标题
     * @param memo   待办的内容
     * @return
     */
    public boolean insertReadDo(String userId,String title ,String memo,String creator,String updater);
}
