package com.sise.shop.service.impl;

import com.sise.shop.entity.Customer;
import com.sise.shop.mapper.CustomerMapper;
import com.sise.shop.service.ICustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
