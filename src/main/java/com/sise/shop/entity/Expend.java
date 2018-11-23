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
public class Expend extends Model<Expend> {

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
     * 金额
     */
    private Integer SUM;

    /**
     * 创建人
     */
    private String CREATER;

    /**
     * 更新人
     */
    private String UPDATER;

    /**
     * 描述
     */
    private String MEMO;

    /**
     * 支出ID
     */
    private String EXPEND_ID;

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
    public Integer getSUM() {
        return SUM;
    }

    public void setSUM(Integer SUM) {
        this.SUM = SUM;
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
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }
    public String getEXPEND_ID() {
        return EXPEND_ID;
    }

    public void setEXPEND_ID(String EXPEND_ID) {
        this.EXPEND_ID = EXPEND_ID;
    }

    @Override
    protected Serializable pkVal() {
        return this.EXPEND_ID;
    }

    @Override
    public String toString() {
        return "Expend{" +
        "USER_ID=" + USER_ID +
        ", CREATE_TIME=" + CREATE_TIME +
        ", SUM=" + SUM +
        ", CREATER=" + CREATER +
        ", UPDATER=" + UPDATER +
        ", MEMO=" + MEMO +
        ", EXPEND_ID=" + EXPEND_ID +
        "}";
    }
}
