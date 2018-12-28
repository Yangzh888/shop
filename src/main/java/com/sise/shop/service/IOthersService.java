package com.sise.shop.service;

import com.sise.shop.entity.Others;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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

}
