package com.biz.lesson.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.lesson.CacheConstants;
import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.model.user.User;

/**
 * 用户缓存适配器
 */
@Component
public class UserCacheAdapter extends AbstractCacheAdapter {


    private static final Logger logger = LoggerFactory.getLogger(UserCacheAdapter.class);
    
    @Autowired
    private UserManager userManager;

    private List<User> users = new ArrayList<>();

 
    protected void init() {
        careCacheEventTypes = new CacheEventType[] {CacheEventType.USER };
        loadUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void loadUsers() {
        users = userManager.listEnableUsers();
        application.setAttribute(CacheConstants.USERS, users);
        logger.info("load user list size:{}", users.size());
    }

    @Override
    protected void refresh(CacheEventType cacheEventType) {
        this.loadUsers();
    }


}
