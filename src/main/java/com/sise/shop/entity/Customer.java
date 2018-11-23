package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String USER_ID;

    /**
     * 客户ID
     */
    private String CUSTOMER_ID;

    /**
     * 客户姓名
     */
    private String CUSTOMER_NAME;

    /**
     * 联系电话
     */
    private String CONTACT_PHONE;

    /**
     * 客户地址
     */
    private String CUSTOMER_ADDRESS;

    /**
     * 创建时间
     */
    private Date CREATE_TIME;

    /**
     * 信息描述
     */
    private String MEMO;

    /**
     * 金额
     */
    private Integer SUM;

    /**
     * 状态
     */
    private String CUSTOMER_STATUS;

    /**
     * 关联商品ID外键
     */
    private String GOODS_ID;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }
    public String getCONTACT_PHONE() {
        return CONTACT_PHONE;
    }

    public void setCONTACT_PHONE(String CONTACT_PHONE) {
        this.CONTACT_PHONE = CONTACT_PHONE;
    }
    public String getCUSTOMER_ADDRESS() {
        return CUSTOMER_ADDRESS;
    }

    public void setCUSTOMER_ADDRESS(String CUSTOMER_ADDRESS) {
        this.CUSTOMER_ADDRESS = CUSTOMER_ADDRESS;
    }
    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }
    public Integer getSUM() {
        return SUM;
    }

    public void setSUM(Integer SUM) {
        this.SUM = SUM;
    }
    public String getCUSTOMER_STATUS() {
        return CUSTOMER_STATUS;
    }

    public void setCUSTOMER_STATUS(String CUSTOMER_STATUS) {
        this.CUSTOMER_STATUS = CUSTOMER_STATUS;
    }
    public String getGOODS_ID() {
        return GOODS_ID;
    }

    public void setGOODS_ID(String GOODS_ID) {
        this.GOODS_ID = GOODS_ID;
    }

    @Override
    protected Serializable pkVal() {
        return this.CUSTOMER_ID;
    }

    @Override
    public String toString() {
        return "Customer{" +
        "USER_ID=" + USER_ID +
        ", CUSTOMER_ID=" + CUSTOMER_ID +
        ", CUSTOMER_NAME=" + CUSTOMER_NAME +
        ", CONTACT_PHONE=" + CONTACT_PHONE +
        ", CUSTOMER_ADDRESS=" + CUSTOMER_ADDRESS +
        ", CREATE_TIME=" + CREATE_TIME +
        ", MEMO=" + MEMO +
        ", SUM=" + SUM +
        ", CUSTOMER_STATUS=" + CUSTOMER_STATUS +
        ", GOODS_ID=" + GOODS_ID +
        "}";
    }
}
