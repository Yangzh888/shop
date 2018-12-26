package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    @TableId
    private String userId;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 联系电话
     */
    private String customerPhone;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 信息描述
     */
    private String memo;

    /**
     * 金额
     */
    private Integer sum;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态
     */
    private String customerSataus;

    /**
     * 关联商品ID外键
     */
    private String goodsId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCustomerSataus() {
        return customerSataus;
    }

    public void setCustomerSataus(String customerSataus) {
        this.customerSataus = customerSataus;
    }
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
        "userId=" + userId +
        ", customerId=" + customerId +
        ", customerName=" + customerName +
        ", customerPhone=" + customerPhone +
        ", customerAddress=" + customerAddress +
        ", createTime=" + createTime +
        ", memo=" + memo +
        ", sum=" + sum +
        ", creator=" + creator +
        ", customerSataus=" + customerSataus +
        ", goodsId=" + goodsId +
        "}";
    }
}
