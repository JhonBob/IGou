package com.bob.igou.bean;

/**
 * Created by Administrator on 2016/3/15.
 */
//用户数据封装
public class UserData {
    public String userName;
    public String userPwd;
    private int userId;

    public UserData(String userName,String userPwd){
        super();
        this.userName=userName;
        this.userPwd=userPwd;
}

    public UserData(String userName,String userPwd,int userId){
        super();
        this.userName=userName;
        this.userPwd=userPwd;
        this.userId=userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userId=" + userId +
                '}';
    }
}
