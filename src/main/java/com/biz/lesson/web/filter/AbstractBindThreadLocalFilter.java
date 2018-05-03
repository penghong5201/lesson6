package com.biz.lesson.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.biz.lesson.web.servlet.RequestResponseHolder;

import java.io.IOException;

public abstract class AbstractBindThreadLocalFilter<T extends RequestResponseHolder> implements Filter {

    private static ThreadLocal<RequestResponseHolder> filterThreadLocal = new ThreadLocal<RequestResponseHolder>();

    public static <T extends RequestResponseHolder> T getRequestResponseHolder() {
        @SuppressWarnings("unchecked") T holder = (T) filterThreadLocal.get();
        return holder;
    }

    public abstract T buildRequestResponseHolder();

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException;

    @Override
    public final void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException {

        RequestResponseHolder holder = buildRequestResponseHolder();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        holder.setRequest(httpServletRequest);
        holder.setResponse(httpServletResponse);
        
        filterThreadLocal.set(holder);
        holder.afterSetRequest();
        request.setAttribute("originRequest", ((HttpServletRequest) request).getRequestURI() + (StringUtils.isBlank(((HttpServletRequest) request).getQueryString()) ? "" : "?" + ((HttpServletRequest) request).getQueryString()));
      
        try{
            doFilter(httpServletRequest, httpServletResponse, filterChain);
        }catch (Throwable e) {
           e.printStackTrace();
        }
        
        
    }

}