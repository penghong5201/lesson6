package com.biz.lesson.business.user;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.biz.lesson.model.user.User;
import com.biz.lesson.model.user.UserLoginLog;
import com.biz.lesson.model.user.UserRequest;
import com.biz.lesson.util.DateUtil;
import com.biz.lesson.web.servlet.HttpServletHelper;

@Service
public class UserHelper {

    @Autowired
    private UserManager userManager;

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 在线访问信息
     */
    private Map<String, UserRequest> userRequestMap = new ConcurrentHashMap<>();

    /**
     * 记录登陆日志
     */
    public void logLogin(User user, HttpServletRequest request) {
        String formIP = null;
        if (request != null) {
            formIP = HttpServletHelper.getClientIP(request);
        }
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUser(user);
        userLoginLog.setLoginTime(DateUtil.getDateTime());
        formIP = StringUtils.left(formIP, 40);
        userLoginLog.setLoginip(formIP);
        userLoginLog.setActionType(UserLoginLog.LOGIN);
        userManager.saveUserLoginLog(userLoginLog);
    }

    /**
     * 判断ip是否可以登录
     */
    public boolean isIpPermit(User user, HttpServletRequest request) {
        String formIP = request.getRemoteAddr();
        String permitIP = user.getPermitIP();
        if (StringUtils.isBlank(permitIP)) {
            return true;
        } else{
              return true;
//            return StringUtil.isPermit(formIP, permitIP);
        }
    }

    public void doLogin(User user, HttpServletRequest request) {
        logLogin(user, request);

        UserRequest userRequest = new UserRequest();
        userRequest.setLastActiveTime(DateUtil.getDateTime());
        if (request != null) {
            userRequest.setLoginIP(HttpServletHelper.getClientIP(request));
            userRequest.setLastRequest(request.getRequestURI());
        }

        userRequestMap.put(user.getId(), userRequest);
    }

    /**
     * 注销的清理工作 记录注销时间
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */
    public void doLogout(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setUser(userManager.getUser(userId));
            userLoginLog.setLoginTime(DateUtil.getDateTime());
            userLoginLog.setActionType(UserLoginLog.LOGOUT);

            userRequestMap.remove(userId);
        }
    }

    public Map<String, UserRequest> getUserRequestMap() {
        return userRequestMap;
    }

    @SuppressWarnings("rawtypes")
    public List getOnlineUsers() {
        return sessionRegistry.getAllPrincipals();
    }

    public void accessRequest(String userId, final HttpServletRequest request) {
        UserRequest userRequest = userRequestMap.get(userId);
        if (userRequest != null) {
            userRequest.setLastActiveTime(DateUtil.getDateTime());
            userRequest.setLoginIP(HttpServletHelper.getClientIP(request));
            userRequest.setLastRequest(request.getRequestURI());
        }
    }

}
