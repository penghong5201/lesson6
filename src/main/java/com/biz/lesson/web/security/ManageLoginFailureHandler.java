package com.biz.lesson.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.biz.lesson.business.user.LoginAttemptService;
import com.biz.lesson.web.servlet.HttpServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.biz.lesson.business.user.LoginAttemptService.LOGIN_USER_BLOCKED_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

public class ManageLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler implements
        org.springframework.security.web.authentication.AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(ManageLoginFailureHandler.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String key = HttpServletHelper.getClientIP(request);
        String user = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

        logger.debug("用户"+user+"在IP地址为:"+key+"登录");

        if (loginAttemptService.isIPBlocked(key) || loginAttemptService.isUserBlocked(user)){
            request.getSession().setAttribute(LOGIN_USER_BLOCKED_KEY,true);
        }else{
            loginAttemptService.loginFailed(key);
            loginAttemptService.loginFailed(user);
            request.getSession().setAttribute(LOGIN_USER_BLOCKED_KEY,false);
        }

        super.onAuthenticationFailure(request,response,exception);


    }
}
