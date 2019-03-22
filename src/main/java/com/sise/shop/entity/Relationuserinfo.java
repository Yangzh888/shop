package com.sise.shop.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * <p>
 * 用户子表
 * </p>
 *
 * @author yangzhenhua
 * @since 2019-03-17
 */
public class Relationuserinfo extends Model<Relationuserinfo> implements java.io.Serializable {

    public String getRelationUserInfoId() {
        return relationUserInfoId;
    }

    public void setRelationUserInfoId(String relationUserInfoId) {
        this.relationUserInfoId = relationUserInfoId;
    }

    public String getRelationUserInfoName() {
        return relationUserInfoName;
    }

    public void setRelationUserInfoName(String relationUserInfoName) {
        this.relationUserInfoName = relationUserInfoName;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户账号
     */
    private String userId;

    /**
     * 用户子表Id
     */
    @TableId
    private String relationUserInfoId;

    /**
     * 权限等级 5级，级数越高权限越高
     * 0-只能修改自己的数据
     * 1-可以修改别人的数据
     * 2-可以删除别人的数据
     * 3-可以删除、修改别人的数据
     * 4-出权限分配外，其他功能都能使用-数据分析等都能用、、、
     * 5-管理员权限-所有操作
     */
    private Integer level;
    private String password;
    private String relationUserInfoName;
    private String goodslevel;
    private String readyDolevel;
    private String customerlevel;
    private String comeAndOutlevel;
    private String anaylyslevel;
    private String permissionlevel;
private String createTime;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGoodslevel() {
        return goodslevel;
    }

    public void setGoodslevel(String goodslevel) {
        this.goodslevel = goodslevel;
    }

    public String getReadyDolevel() {
        return readyDolevel;
    }

    public void setReadyDolevel(String readyDolevel) {
        this.readyDolevel = readyDolevel;
    }

    public String getCustomerlevel() {
        return customerlevel;
    }

    public void setCustomerlevel(String customerlevel) {
        this.customerlevel = customerlevel;
    }

    public String getComeAndOutlevel() {
        return comeAndOutlevel;
    }

    public void setComeAndOutlevel(String comeAndOutlevel) {
        this.comeAndOutlevel = comeAndOutlevel;
    }

    public String getAnaylyslevel() {
        return anaylyslevel;
    }

    public void setAnaylyslevel(String anaylyslevel) {
        this.anaylyslevel = anaylyslevel;
    }

    public String getPermissionlevel() {
        return permissionlevel;
    }

    public void setPermissionlevel(String permissionlevel) {
        this.permissionlevel = permissionlevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    protected Serializable pkVal() {
        return this.relationUserInfoId;
    }

    @Override
    public String toString() {
        return "Relationuserinfo{" +
                "userId=" + userId +
                ", RelationUserInfoId=" + relationUserInfoId +
                ", level=" + level +
                "}";
    }
}
