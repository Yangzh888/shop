package com.sise.shop.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
public class Userinfo extends Model<Userinfo> implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户账号
     */
    @NotNull(message="用户名不允许为空")
    @TableId
    private String userId;

    /**
     * 联系电话
     */
    private String userPhone;

    /**
     * 用户密码
     */
    @NotNull(message="用户名不允许为空")
    private String password;

    /**
     * 忘记密码问题
     */
    private String forgetQue;

    /**
     * 忘记密码答案
     */
    private String forgetAns;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 等级
     */
    private String rank;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 关联客户ID
     */
    private String customerId;

    /**
     * 收入ID
     */
    private String incomeId;

    /**
     * 支出ID
     */
    private String espendId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 其他ID
     */
    private String othersId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getForgetQue() {
        return forgetQue;
    }

    public void setForgetQue(String forgetQue) {
        this.forgetQue = forgetQue;
    }
    public String getForgetAns() {
        return forgetAns;
    }

    public void setForgetAns(String forgetAns) {
        this.forgetAns = forgetAns;
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }
    public String getEspendId() {
        return espendId;
    }

    public void setEspendId(String espendId) {
        this.espendId = espendId;
    }
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getOthersId() {
        return othersId;
    }

    public void setOthersId(String othersId) {
        this.othersId = othersId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
        "username=" + username +
        ", userId=" + userId +
        ", userPhone=" + userPhone +
        ", password=" + password +
        ", forgetQue=" + forgetQue +
        ", forgetAns=" + forgetAns +
        ", shopName=" + shopName +
        ", rank=" + rank +
        ", createTime=" + createTime +
        ", creator=" + creator +
        ", customerId=" + customerId +
        ", incomeId=" + incomeId +
        ", espendId=" + espendId +
        ", goodsId=" + goodsId +
        ", othersId=" + othersId +
        "}";
    }
}
