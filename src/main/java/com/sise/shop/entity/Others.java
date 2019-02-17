package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 其他表
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public class Others extends Model<Others> implements java.io.Serializable  {

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
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String memo;

    /**
     * 提示
     */
    private String tip;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 联系电话
     */
    private String othersPhone;

    /**
     * 其他信息表ID
     */
    @TableId
    private String othersId;

    /**
     * 状态
     * @return
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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
    public String getOthersPhone() {
        return othersPhone;
    }

    public void setOthersPhone(String othersPhone) {
        this.othersPhone = othersPhone;
    }
    public String getOthersId() {
        return othersId;
    }

    public void setOthersId(String othersId) {
        this.othersId = othersId;
    }

    @Override
    protected Serializable pkVal() {
        return this.othersId;
    }

    @Override
    public String toString() {
        return "Others{" +
        "userId=" + userId +
        ", createTime=" + createTime +
        ", title=" + title +
        ", memo=" + memo +
        ", tip=" + tip +
        ", creator=" + creator +
        ", updater=" + updater +
        ", othersPhone=" + othersPhone +
        ", othersId=" + othersId +
        "}";
    }
}
