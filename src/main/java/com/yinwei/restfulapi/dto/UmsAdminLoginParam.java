package com.yinwei.restfulapi.dto;

import javax.validation.constraints.NotNull;

/**
 * create by: yinwei
 * description: TODO 用户登录参数
 * create time: 2021/7/27 9:29
 */
public class UmsAdminLoginParam {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;

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
}
