package com.sise.shop.service;

import com.sise.shop.entity.Income;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 支出表 服务类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface IIncomeService extends IService<Income> {

    List<Income> selectTitle(String title);

}
