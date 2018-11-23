package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 支出表
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class Income extends Model<Income> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String USER_ID;

    /**
     * 创建时间
     */
    private Date CREATE_TIME;

    /**
     * 标题
     */
    private String TITLE;

    /**
     * 金额
     */
    private Integer SUM;

    /**
     * 描述
     */
    private String MEMO;

    /**
     * 创建人
     */
    private String CREATER;

    /**
     * 更新人
     */
    private String UPDATER;

    /**
     * 收入ID
     */
    private String INCOME_ID;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
    public Integer getSUM() {
        return SUM;
    }

    public void setSUM(Integer SUM) {
        this.SUM = SUM;
    }
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
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
    public String getINCOME_ID() {
        return INCOME_ID;
    }

    public void setINCOME_ID(String INCOME_ID) {
        this.INCOME_ID = INCOME_ID;
    }

    @Override
    protected Serializable pkVal() {
        return this.INCOME_ID;
    }

    @Override
    public String toString() {
        return "Income{" +
        "USER_ID=" + USER_ID +
        ", CREATE_TIME=" + CREATE_TIME +
        ", TITLE=" + TITLE +
        ", SUM=" + SUM +
        ", MEMO=" + MEMO +
        ", CREATER=" + CREATER +
        ", UPDATER=" + UPDATER +
        ", INCOME_ID=" + INCOME_ID +
        "}";
    }
}
