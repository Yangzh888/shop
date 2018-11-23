package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class User_info extends Model<User_info> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户姓名
     */
    private String USER_NAME;

    /**
     * 用户账号
     */
    @TableId
    private String USER_ID;

    /**
     * 联系电话
     */
    private String CONTACT_PHONE;

    /**
     * 用户密码
     */
    private String USER_PASSWORD;

    /**
     * 忘记密码问题
     */
    private String FORGET_QUESTION;

    /**
     * 忘记密码答案
     */
    private String FORGET_ANSWER;

    /**
     * 店铺名称
     */
    private String SHOP_NAME;

    /**
     * 等级
     */
    private String RANK;

    /**
     * 创建时间
     */
    private Date CREATE_TIME;

    private String CUSTOMER_ID;

    private String GOODS_ID;

    private String INCOME_ID;

    private String EXPEND_ID;

    private String OTHERS_ID;

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }
    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    public String getCONTACT_PHONE() {
        return CONTACT_PHONE;
    }

    public void setCONTACT_PHONE(String CONTACT_PHONE) {
        this.CONTACT_PHONE = CONTACT_PHONE;
    }
    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }
    public String getFORGET_QUESTION() {
        return FORGET_QUESTION;
    }

    public void setFORGET_QUESTION(String FORGET_QUESTION) {
        this.FORGET_QUESTION = FORGET_QUESTION;
    }
    public String getFORGET_ANSWER() {
        return FORGET_ANSWER;
    }

    public void setFORGET_ANSWER(String FORGET_ANSWER) {
        this.FORGET_ANSWER = FORGET_ANSWER;
    }
    public String getSHOP_NAME() {
        return SHOP_NAME;
    }

    public void setSHOP_NAME(String SHOP_NAME) {
        this.SHOP_NAME = SHOP_NAME;
    }
    public String getRANK() {
        return RANK;
    }

    public void setRANK(String RANK) {
        this.RANK = RANK;
    }
    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
    public String getGOODS_ID() {
        return GOODS_ID;
    }

    public void setGOODS_ID(String GOODS_ID) {
        this.GOODS_ID = GOODS_ID;
    }
    public String getINCOME_ID() {
        return INCOME_ID;
    }

    public void setINCOME_ID(String INCOME_ID) {
        this.INCOME_ID = INCOME_ID;
    }
    public String getEXPEND_ID() {
        return EXPEND_ID;
    }

    public void setEXPEND_ID(String EXPEND_ID) {
        this.EXPEND_ID = EXPEND_ID;
    }
    public String getOTHERS_ID() {
        return OTHERS_ID;
    }

    public void setOTHERS_ID(String OTHERS_ID) {
        this.OTHERS_ID = OTHERS_ID;
    }

    @Override
    protected Serializable pkVal() {
        return this.USER_ID;
    }

    @Override
    public String toString() {
        return "User_info{" +
        "USER_NAME=" + USER_NAME +
        ", USER_ID=" + USER_ID +
        ", CONTACT_PHONE=" + CONTACT_PHONE +
        ", USER_PASSWORD=" + USER_PASSWORD +
        ", FORGET_QUESTION=" + FORGET_QUESTION +
        ", FORGET_ANSWER=" + FORGET_ANSWER +
        ", SHOP_NAME=" + SHOP_NAME +
        ", RANK=" + RANK +
        ", CREATE_TIME=" + CREATE_TIME +
        ", CUSTOMER_ID=" + CUSTOMER_ID +
        ", GOODS_ID=" + GOODS_ID +
        ", INCOME_ID=" + INCOME_ID +
        ", EXPEND_ID=" + EXPEND_ID +
        ", OTHERS_ID=" + OTHERS_ID +
        "}";
    }
}
