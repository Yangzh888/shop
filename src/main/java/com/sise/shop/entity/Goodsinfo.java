package com.sise.shop.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-13
 */
public class Goodsinfo extends Model<Goodsinfo>  implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String userId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 商品名称
     */
    private String tradeName;

    /**
     * 类别
     */
    private String type;

    /**
     * 利润
     */
    private Integer profit;
    /**
     * 警告库存
     */
    private Integer warmingNumber;
    /**
     * 存放位置
     */
    private String location;

    /**
     * 商品数量
     */
    public Integer number;

    /**默认该字段为创建人Id*/
    private String creator;
    /**默认该字段为创建人姓名*/
    private String updater;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWarmingNumber() {
        return warmingNumber;
    }

    public void setWarmingNumber(Integer warmingNumber) {
        this.warmingNumber = warmingNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 商品信息Id
     */
    @TableId

    private String goodsInfoId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }
    public String getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(String goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    @Override
    protected Serializable pkVal() {
        return this.goodsInfoId;
    }

    @Override
    public String toString() {
        return "Goodsinfo{" +
        "userId=" + userId +
        ", createTime=" + createTime +
        ", tradeName=" + tradeName +
        ", type=" + type +
        ", profit=" + profit +
        ", goodsInfoId=" + goodsInfoId +
        "}";
    }
}
