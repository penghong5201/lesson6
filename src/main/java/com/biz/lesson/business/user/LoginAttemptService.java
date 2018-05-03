package com.biz.lesson.business.user;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * 处理用户重试次数的逻辑
 * 三次 输入验证码,10次锁定IP 不许这个IP登陆
 */
@Service
public class LoginAttemptService {
    public static final String LOGIN_USER_BLOCKED_KEY = "userBlocked";
    private final int USER_ATTEMPT = 3;
    private final int CLIENT_ATTEMPT = 10;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isIPBlocked(String key) {
        try {
            return attemptsCache.get(key).intValue() >= CLIENT_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public boolean isUserBlocked(String key) {
        try {
            return attemptsCache.get(key).intValue() >= USER_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}