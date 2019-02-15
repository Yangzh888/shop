package com.sise.shop.mapper;

import com.sise.shop.entity.Goods;
import com.sise.shop.entity.Goodsinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-13
 */
public interface GoodsinfoMapper extends BaseMapper<Goodsinfo> {
    @Update("update goodsInfo set number=#{number} where goodsInfoId=#{goodsInfoId}")
    public void updateGoodsInfoNumber(@Param("goodsInfoId")String goodsInfoId, @Param("number")int number);
}
