package com.sise.shop.mapper;

import com.sise.shop.entity.Income;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 支出表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public interface IncomeMapper extends BaseMapper<Income> {

    @Select("select * from  income where title like CONCAT('%',#{title},'%') or creator like CONCAT('%',#{title},'%') ")
    List<Income> selectTitle(String title);
}
