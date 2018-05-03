package com.biz.lesson.business.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.biz.lesson.model.user.Role;
import com.biz.lesson.model.user.User;

import java.util.Collection;
import java.util.List;

/**
 * 鉴权公共类
 */
public class AuthorityUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorityUtil.class);

    public static String getLoginUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        } else {
            return null;
        }
    }

    /**
     * 获取当前登录用户
     * @return
     * @author defei
     * @date 2015-4-13
     */
    public static User getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof User ? (User) principal : null;
    }


    /**
     * 判断当前用户是否拥有角色
     *
     * @param roleName 角色名称
     * @return
     * @author defei
     * @date 2015-4-13
     */
    public static boolean hasRole(String roleName) {
        if (roleName == null) {
            LOG.warn("roleName is null");
            return false;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || !(principal instanceof User)) {
            return false;
        }

        List<Role> roleList = ((User) principal).getRoles();
        if (roleList == null || roleList.isEmpty()) {
            return false;
        }

        for (Role role : roleList) {
            if (role != null && roleName.equals(role.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断用户是否拥有这个权限
     */
    public static boolean hasAuthentication(User admin, String authenticationName){
        if (authenticationName == null) {
            LOG.warn("roleName is null");
            return false;
        }

        if (admin == null) {
            return false;
        }

        Collection<GrantedAuthority> authorities = admin.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }

        for (GrantedAuthority authority : authorities) {
            if (authority != null && authenticationName.equals(authority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

}
