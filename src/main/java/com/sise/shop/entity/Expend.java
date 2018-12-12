package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 支出表
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public class Expend extends Model<Expend> {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 金额
     */
    private Integer sum;

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
     * 支出ID
     */
    private String espendId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public String getEspendId() {
        return espendId;
    }

    public void setEspendId(String espendId) {
        this.espendId = espendId;
    }

    @Override
    protected Serializable pkVal() {
        return this.espendId;
    }

    @Override
    public String toString() {
        return "Expend{" +
        "userId=" + userId +
        ", createTime=" + createTime +
        ", sum=" + sum +
        ", creator=" + creator +
        ", updater=" + updater +
        ", memo=" + memo +
        ", espendId=" + espendId +
        "}";
    }
}
