package com.biz.lesson.web.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.biz.lesson.Constants;
import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.User;
import com.biz.lesson.web.servlet.DataStorage;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;

public class DataStorageInterceptor implements WebRequestInterceptor {

    @Autowired
    public UserManager userManager;
    
    @Override
    public void preHandle(WebRequest request) throws Exception {
        String userId = request.getParameter(Constants.PARAM_USERID);
        if(StringUtils.isNotBlank(userId)){
            User user = userManager.getUser(userId);
            DataStorage ds =  DataStorageThreadLocalHolder.getDataStorage();
            ds.setUser(user);
        }
    }

    @Override
    public void afterCompletion(WebRequest request, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }
}
