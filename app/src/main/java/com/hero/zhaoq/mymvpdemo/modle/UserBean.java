package com.hero.zhaoq.mymvpdemo.modle;

/**
 * Package_name:com.hero.zhaoq.mymvpdemo.modle
 * Author:zhaoQiang
 * Email:zhaoq_hero@163.com
 * Date:2017/03/25  14:19
 * 用户  实体类
 */
public class UserBean {

    private String username;
    private String userPassword;

    public UserBean(String username, String password) {
        this.username = username;
        this.userPassword = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
