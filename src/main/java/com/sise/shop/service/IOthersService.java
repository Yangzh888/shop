package com.sise.shop.service;

import com.sise.shop.entity.Others;
import com.baomidou.mybatisplus.service.IService;
import com.sise.shop.utilis.result.Result;

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
    public  Result saveReadyDo(Map formLabelAlign);
}
