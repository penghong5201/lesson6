package com.biz.lesson.web.interceptor;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.biz.lesson.business.user.AccessLogService;
import com.biz.lesson.business.user.AuthorityUtil;
import com.biz.lesson.business.user.UserHelper;
import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.util.DateUtil;

public class AccessLogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

	@Autowired
	private AccessLogService accessLogService;
	
    @Autowired
    private UserHelper userHelper;

	private String excludedUri;

	private Boolean manualEnable = true;

	/**
	 * 使用URI 匹配需要记录log的正则表达式
	 */
	private boolean withinLog(String uri) {

		return uri.trim().matches(excludedUri);
	}

	/**
	 * 日志记录实现
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		
		
		
		Boolean enabled = manualEnable;
		if (enabled && !withinLog(request.getRequestURI())) {
			String user = AuthorityUtil.getLoginUsername();
			String uri = request.getRequestURI();
			
			userHelper.accessRequest(user, request);
			
			Timestamp timeStamp = DateUtil.getDateTime();
			String http_method = request.getMethod();
			JSONObject params = new JSONObject();
			Enumeration<String> parameterNames = request.getParameterNames();
			for (; parameterNames.hasMoreElements(); ) {
				String thisName = ((Enumeration) parameterNames).nextElement().toString();
				String thisValue = StringUtils.join(request.getParameterValues(thisName), ",");
				params.put(thisName, thisValue);
			}
			String requestParams = params.toJSONString();
			AccessLogPo accessLogPo = new AccessLogPo();
			accessLogPo.setUri(uri);
			accessLogPo.setTimestamp(timeStamp);
			accessLogPo.setUser(user);
			accessLogPo.setHttp_method(http_method);
			accessLogPo.setRequestParams(requestParams);
			if(logger.isDebugEnabled()){
				logger
				  .debug("uri:{}\nmethod:{}\nuser:{}\ntimestamp:{}\nparams:{}", accessLogPo.getUri(), accessLogPo.getHttp_method(), accessLogPo.getUser(),
					accessLogPo.getTimestamp(), accessLogPo.getRequestParams());
				logger.debug("preHadle{} loginfo", uri);
			}
			accessLogService.saveLog(accessLogPo);
		}
		return true;
	}

	public void setExcludedUri(String excludedUri) {

		this.excludedUri = excludedUri;
	}

	public void setEnabled(boolean enabled) {

		this.manualEnable = enabled;
	}
}
