package com.sise.shop.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 批发商表
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-02-14
 */
public class Wholesaler extends Model<Wholesaler> implements java.io.Serializable{

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
     * 批发商名称
     */
    private String wholesalerName;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * ID
     */
    @TableId
    private String wholesalerId;

    /**
     * 状态-判断是客户还是批发商
     */
    private String status;

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
    public String getWholesalerName() {
        return wholesalerName;
    }

    public void setWholesalerName(String wholesalerName) {
        this.wholesalerName = wholesalerName;
    }
    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(String wholesalerId) {
        this.wholesalerId = wholesalerId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.wholesalerId;
    }

    @Override
    public String toString() {
        return "Wholesaler{" +
        "userId=" + userId +
        ", createTime=" + createTime +
        ", wholesalerName=" + wholesalerName +
        ", linkMan=" + linkMan +
        ", phone=" + phone +
        ", address=" + address +
        ", businessScope=" + businessScope +
        ", memo=" + memo +
        ", wholesalerId=" + wholesalerId +
        ", status=" + status +
        "}";
    }
}
