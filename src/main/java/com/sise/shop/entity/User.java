package com.sise.shop.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Mht
 * @since 2018-11-20
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;


    private Integer id;
    @NotNull(message="账号不允许为空")
    private String username;

    @NotNull(message="密码不允许为空")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
