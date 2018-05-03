package com.biz.lesson.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.biz.lesson.model.user.User;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;

public class URLRouterInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(URLRouterInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getSession().getServletContext().getContextPath();
        requestUri = StringUtils.removeStart(requestUri,contextPath );

        User user = DataStorageThreadLocalHolder.getPossibleUser();
        if (user == null) {
            return true;
        }else{
            logger.debug("user is not null");
            return true;
        }
    }
}
