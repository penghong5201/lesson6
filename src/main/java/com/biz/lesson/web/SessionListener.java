package com.biz.lesson.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.biz.lesson.business.user.SessionContext;

public class SessionListener implements HttpSessionListener {
    public  static SessionContext sessionContext=SessionContext.getInstance();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        sessionContext.addSession(httpSessionEvent.getSession());

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        sessionContext.delSession(httpSessionEvent.getSession());
    }

}
