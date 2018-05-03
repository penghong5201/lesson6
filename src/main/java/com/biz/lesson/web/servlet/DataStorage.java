package com.biz.lesson.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.biz.lesson.model.user.User;

/**
 * 放在ThreadLocal 中的对象
 */
public final class DataStorage {

    /**
     * 当前语言是否是中文(包含简体，台湾，香港)
     */
    private Boolean isZH;

    private User user;

    private HttpServletRequest request;

    /**
     * {@linkplain DataStorage#isZH}
     */
    public Boolean isZH() {

        return isZH;
    }

    /**
     * {@linkplain DataStorage#isZH}
     */
    public void setIsZH(Boolean ZH) {

        isZH = ZH;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    
}
