package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String USER_ID;

    /**
     * 商品ID
     */
    private String GOODS_ID;

    /**
     * 商品名称
     */
    private String CUSTOMER_NAME;

    /**
     * 流水号
     */
    private String IDENTIFIER;

    /**
     * 来源
     */
    private String GOODS_FROM;

    /**
     * 所在位置
     */
    private String LOCATION;

    /**
     * 创建时间
     */
    private Date CREATE_TIME;

    /**
     * 创建人
     */
    private String CREATER;

    /**
     * 更新人
     */
    private String UPDATER;

    /**
     * 单价
     */
    private Integer PRICE;

    /**
     * 数量
     */
    private Integer QUANTITY;

    /**
     * 总价
     */
    private Integer SUM;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    public String getGOODS_ID() {
        return GOODS_ID;
    }

    public void setGOODS_ID(String GOODS_ID) {
        this.GOODS_ID = GOODS_ID;
    }
    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }
    public String getIDENTIFIER() {
        return IDENTIFIER;
    }

    public void setIDENTIFIER(String IDENTIFIER) {
        this.IDENTIFIER = IDENTIFIER;
    }
    public String getGOODS_FROM() {
        return GOODS_FROM;
    }

    public void setGOODS_FROM(String GOODS_FROM) {
        this.GOODS_FROM = GOODS_FROM;
    }
    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
    public String getCREATER() {
        return CREATER;
    }

    public void setCREATER(String CREATER) {
        this.CREATER = CREATER;
    }
    public String getUPDATER() {
        return UPDATER;
    }

    public void setUPDATER(String UPDATER) {
        this.UPDATER = UPDATER;
    }
    public Integer getPRICE() {
        return PRICE;
    }

    public void setPRICE(Integer PRICE) {
        this.PRICE = PRICE;
    }
    public Integer getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(Integer QUANTITY) {
        this.QUANTITY = QUANTITY;
    }
    public Integer getSUM() {
        return SUM;
    }

    public void setSUM(Integer SUM) {
        this.SUM = SUM;
    }

    @Override
    protected Serializable pkVal() {
        return this.GOODS_ID;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "USER_ID=" + USER_ID +
        ", GOODS_ID=" + GOODS_ID +
        ", CUSTOMER_NAME=" + CUSTOMER_NAME +
        ", IDENTIFIER=" + IDENTIFIER +
        ", GOODS_FROM=" + GOODS_FROM +
        ", LOCATION=" + LOCATION +
        ", CREATE_TIME=" + CREATE_TIME +
        ", CREATER=" + CREATER +
        ", UPDATER=" + UPDATER +
        ", PRICE=" + PRICE +
        ", QUANTITY=" + QUANTITY +
        ", SUM=" + SUM +
        "}";
    }
}
