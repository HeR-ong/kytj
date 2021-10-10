package com.herong.demo.controller.req;

import javax.validation.constraints.NotNull;

/**
 * @author herong22384
 * @date 2020/3/22 14:24
 */
public class LoginReq {
    @NotNull
    private String userAccount;
    @NotNull
    private String password;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
