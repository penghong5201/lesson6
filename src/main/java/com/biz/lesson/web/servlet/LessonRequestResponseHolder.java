package com.biz.lesson.web.servlet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.biz.lesson.model.user.User;

public class LessonRequestResponseHolder extends RequestResponseHolder {
    
    private final static Logger logger = LoggerFactory.getLogger(LessonRequestResponseHolder.class);
 
    @Override
    public void afterSetRequest() {
        HttpServletRequest request = getRequest();
        DataStorage dataStorage = DataStorageThreadLocalHolder.getDataStorage();
        if (dataStorage != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User) {
                dataStorage.setUser((User) authentication.getPrincipal());
            }
            dataStorage.setRequest(request);
        }
        super.afterSetRequest();
    }

    public LessonRequestResponseHolder(DataStorage dataStorage) {
        DataStorageThreadLocalHolder.setDataStorage(dataStorage);
    }



}
