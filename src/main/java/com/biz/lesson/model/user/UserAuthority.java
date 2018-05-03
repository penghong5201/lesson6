package com.biz.lesson.model.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * 保存起来的权限
 */
public class UserAuthority implements GrantedAuthority, Comparable {

    private static final long serialVersionUID = 3655416701880696014L;
    private String auth = null;

    public UserAuthority(String auth) {
        super();
        this.auth = auth;
    }

    public int compareTo(Object o) {
        UserAuthority oAuth = (UserAuthority) o;
        if (oAuth == null || oAuth.getAuthority() == null)
            return -1;
        return oAuth.getAuthority().compareTo(this.auth);
    }

    public int hashCode() {
        return auth.hashCode();
    }

    public boolean equals(Object o) {
        UserAuthority oAuth = (UserAuthority) o;
        return !(oAuth == null || oAuth.getAuthority() == null) && oAuth.getAuthority().equals(this.auth);
    }

    public String getAuthority() {
        return auth;
    }

}
