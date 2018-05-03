package com.biz.lesson.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.biz.lesson.business.user.LoginAttemptService;
import com.biz.lesson.business.user.UserHelper;
import com.biz.lesson.model.config.Config;
import com.biz.lesson.model.user.User;
import com.biz.lesson.web.SessionListener;
import com.biz.lesson.web.servlet.HttpServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.biz.lesson.business.user.LoginAttemptService.LOGIN_USER_BLOCKED_KEY;
import static com.biz.lesson.business.user.SessionContext.SESSION_CONTEXT_USER_KEY;

import java.io.IOException;


public class ManageLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
  implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private LoginAttemptService loginAttemptService;

	public ManageLoginSuccessHandler() {

	}

	public ManageLoginSuccessHandler(String defaultTargetUrl) {
		this.setDefaultTargetUrl(defaultTargetUrl);
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	  throws IOException, ServletException {

	    
		super.onAuthenticationSuccess(request, response, authentication);

		//登录成功操作
		User user = (User) authentication.getPrincipal();
		userHelper.doLogin(user, request);

		loginAttemptService.loginSucceeded(HttpServletHelper.getClientIP(request));
		loginAttemptService.loginSucceeded(((User) authentication.getPrincipal()).getUserId());
		request.getSession().removeAttribute(LOGIN_USER_BLOCKED_KEY);

		//登录成功,下线其他用户
		if (!Config.getInstance().getAllowMultipleLogin()){
			sessionHandlerWhenLoginSuccess(request.getSession(),user.getUserId());
		}
	}

	public void sessionHandlerWhenLoginSuccess(HttpSession session, String userId){
		if (userId == null) return;
		if(SessionListener.sessionContext.getSessionMap().get(userId) != null){
			HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(userId);
			//注销在线用户
			userSession.invalidate();

			SessionListener.sessionContext.getSessionMap().remove(userId);

			//清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
			SessionListener.sessionContext.getSessionMap().put(userId,session);

			//添加userId属性,方便在sessionDestroyed的时候,通过userId寻找
			session.setAttribute(SESSION_CONTEXT_USER_KEY, userId);

		}
		else
		{
			// 根据当前sessionid 取session对象。 更新map key=用户名 value=session对象 删除map
			HttpSession userSession = SessionListener.sessionContext.getSession(session.getId());
			SessionListener.sessionContext.getSessionMap().put(userId,userSession);

			session.setAttribute(SESSION_CONTEXT_USER_KEY, userId);

			SessionListener.sessionContext.getSessionMap().remove(session.getId());
		}
	}

}
