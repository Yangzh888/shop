package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 其他表
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class Others extends Model<Others> {

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
     * 描述
     */
    private String MEMO;

    /**
     * 提示
     */
    private String TIP;

    /**
     * 创建人
     */
    private String CREATER;

    /**
     * 更新人
     */
    private String UPDATER;

    /**
     * 联系电话
     */
    private String CONTACT_PHONE;

    /**
     * 其他信息表ID
     */
    private String OTHERS_ID;

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
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }
    public String getTIP() {
        return TIP;
    }

    public void setTIP(String TIP) {
        this.TIP = TIP;
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
    public String getCONTACT_PHONE() {
        return CONTACT_PHONE;
    }

    public void setCONTACT_PHONE(String CONTACT_PHONE) {
        this.CONTACT_PHONE = CONTACT_PHONE;
    }
    public String getOTHERS_ID() {
        return OTHERS_ID;
    }

    public void setOTHERS_ID(String OTHERS_ID) {
        this.OTHERS_ID = OTHERS_ID;
    }

    @Override
    protected Serializable pkVal() {
        return this.OTHERS_ID;
    }

    @Override
    public String toString() {
        return "Others{" +
        "USER_ID=" + USER_ID +
        ", CREATE_TIME=" + CREATE_TIME +
        ", TITLE=" + TITLE +
        ", MEMO=" + MEMO +
        ", TIP=" + TIP +
        ", CREATER=" + CREATER +
        ", UPDATER=" + UPDATER +
        ", CONTACT_PHONE=" + CONTACT_PHONE +
        ", OTHERS_ID=" + OTHERS_ID +
        "}";
    }
}
