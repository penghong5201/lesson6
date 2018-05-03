package com.biz.lesson.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

import com.biz.lesson.business.user.UserHelper;
import com.biz.lesson.model.config.Config;
import com.biz.lesson.model.user.User;
import com.biz.lesson.web.SessionListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ManageLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Autowired
    private UserHelper userHelper;
    
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        if(authentication!=null && authentication.getPrincipal()!=null){
            //登出成功操作
            User user = (User) authentication.getPrincipal();
            userHelper.doLogout(user.getUserId());
    
            //首先判断配置中是否允许统一账号多次登录
            if (!Config.getInstance().getAllowMultipleLogin()){
                sessionHandlerWhenLogoutSuccess(request.getSession(), user.getUserId());
            }
        }
        super.handle(request, response, authentication);
    }

    public void sessionHandlerWhenLogoutSuccess(HttpSession session, String userId){
        if(userId != null && SessionListener.sessionContext.getSessionMap().get(userId) != null){
            HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(userId);
            //注销当前在线用户
            userSession.invalidate();

            SessionListener.sessionContext.getSessionMap().remove(userId);
            SessionListener.sessionContext.getSessionMap().remove(session.getId());

        }

    }
}
