package com.sise.shop.service.impl;

import com.sise.shop.entity.Income;
import com.sise.shop.mapper.IncomeMapper;
import com.sise.shop.service.IIncomeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 支出表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Service
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IIncomeService {

    @Resource
    private IncomeMapper incomeMapper;
    @Override
    public List<Income> selectTitle(String title) {
        List<Income> incomes = incomeMapper.selectTitle(title);
        return incomes;
    }
}
