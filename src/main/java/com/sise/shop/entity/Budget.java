package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-01-03
 */
public class Budget extends Model<Budget> implements java.io.Serializable   {

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
     * 收入金额
     */
    private Integer inSum;

    /**
     * 支出金额
     */
    private Integer outSum;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 描述
     */
    private String memo;

    /**
     * 收支表ID
     */
    @TableId
    private String budgetId;

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

    public Integer getInSum() {
        return inSum;
    }

    public void setInSum(Integer inSum) {
        this.inSum = inSum;
    }
    public Integer getOutSum() {
        return outSum;
    }

    public void setOutSum(Integer outSum) {
        this.outSum = outSum;
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
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    protected Serializable pkVal() {
        return this.budgetId;
    }

    @Override
    public String toString() {
        return "Budget{" +
        "userId=" + userId +
        ", createTime=" + createTime +
        ", inSum=" + inSum +
        ", outSum=" + outSum +
        ", creator=" + creator +
        ", updater=" + updater +
        ", memo=" + memo +
        ", budgetId=" + budgetId +
        "}";
    }
}
