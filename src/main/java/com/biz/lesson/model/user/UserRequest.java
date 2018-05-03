package com.biz.lesson.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 封装用户 请求记录,用来记录用户行为
 */
public class UserRequest implements Serializable {


    private static final long serialVersionUID = 1841937876301346353L;
    // 登陆后维护的信息
    
    private String loginIP;
    private String lastRequest;
    private Timestamp lastActiveTime;

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(String lastRequest) {
        this.lastRequest = lastRequest;
    }

    public Timestamp getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Timestamp lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

}
