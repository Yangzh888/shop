package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String userId;

    /**
     * 商品ID
     */
    @TableId
    private String goodsId;

    /**
     * 商品名称
     */
    private String tradeName;

    /**
     * 流水号
     */
    private String identifier;

    /**
     * 来源
     */
    private String goodFrom;

    /**
     * 所在位置
     */
    private String location;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 单价
     */
    private Integer price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 总价
     */
    private Integer sum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getGoodFrom() {
        return goodFrom;
    }

    public void setGoodFrom(String goodFrom) {
        this.goodFrom = goodFrom;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
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
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    protected Serializable pkVal() {
        return this.goodsId;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "userId=" + userId +
        ", goodsId=" + goodsId +
        ", tradeName=" + tradeName +
        ", identifier=" + identifier +
        ", goodFrom=" + goodFrom +
        ", location=" + location +
        ", createTime=" + createTime +
        ", creator=" + creator +
        ", updater=" + updater +
        ", price=" + price +
        ", quantity=" + quantity +
        ", sum=" + sum +
        "}";
    }
}
