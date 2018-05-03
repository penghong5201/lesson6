package com.biz.lesson.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.biz.lesson.business.user.UserHelper;
import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginBeforeFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginBeforeFilter.class);


    @Autowired
    private UserManager userManager;

    @Autowired
    private UserHelper userHelper;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = this.obtainUsername(request);
        if(username == null) {
            username = "";
        }
        username = username.trim();
        User user = userManager.getUser(username);

        if(logger.isDebugEnabled()){
            logger.debug("用户:"+username+"当前IP:"+ request.getRemoteAddr());
        }

        if (user == null){
            throw new BadCredentialsException("当前用户不存在");
        }
        if (!userHelper.isIpPermit(user,request)){
            throw new BadCredentialsException("不允许在当前IP登录");
        }
        return super.attemptAuthentication(request, response);

    }
}
