package com.herong.demo.domain.query;

import com.herong.demo.domain.User;

/**
 * @author herong22384
 * @date 2020/3/22 14:13
 */
public class UserQuery extends BasePageQuery<User>{
    private String userName;
    private String userCode;
    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
