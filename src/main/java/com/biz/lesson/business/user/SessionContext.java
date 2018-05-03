package com.biz.lesson.business.user;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用来保存已经登陆的用户
 */

public class SessionContext {
    public static final String SESSION_CONTEXT_USER_KEY = "userId";
    private static SessionContext instance;
    private HashMap<String,HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String,HttpSession>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
            if(session.getAttribute(SESSION_CONTEXT_USER_KEY) != null){
                sessionMap.remove(session.getAttribute(SESSION_CONTEXT_USER_KEY).toString());
            }
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) sessionMap.get(session_id);
    }

    public HashMap<String,HttpSession> getSessionMap() {
        return sessionMap;
    }

}

