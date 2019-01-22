package com.sise.shop;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sise.shop.entity.Budget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ShopApplication.class)
public class ShopApplicationTests {

    @Test
    public void contextLoads() {
    }
@Test
    public Page<Budget> selectPage(){
        Budget budget = new Budget();
        budget.setUserId("123456");
        Page<Budget> page = new Page<Budget>();
        EntityWrapper<Budget> eWrapper = new EntityWrapper<Budget>(budget);
        Page<Budget> budgetList = budget.selectPage(page,eWrapper);

        return  budgetList;
    }
}
