package com.biz.lesson.web.filter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biz.lesson.web.servlet.DataStorage;
import com.biz.lesson.web.servlet.HttpServletHelper;
import com.biz.lesson.web.servlet.LessonRequestResponseHolder;


public class BindThreadLocalFilter extends AbstractBindThreadLocalFilter<LessonRequestResponseHolder> {

    private static final String ACTIVE_MENU_COOKIE_KEY = "activeMenu";

    private static final String ACTIVE_MAIN_MENU_ = "_activeMainMenu_";

    private static final String ACTIVE_SUB_MENU_ = "_activeSubMenu_";

    private Logger logger = LoggerFactory.getLogger(BindThreadLocalFilter.class);

    @Override
    public LessonRequestResponseHolder buildRequestResponseHolder() {
        DataStorage dataStorage = new DataStorage();
        return new LessonRequestResponseHolder(dataStorage);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String activeMenu = HttpServletHelper.getCookieValue(request, ACTIVE_MENU_COOKIE_KEY);
        if (isNotBlank(activeMenu)) {
            String[] activeMenuInfo = activeMenu.split("\\D");
            if (activeMenuInfo.length == 2) {
                request.setAttribute(ACTIVE_MAIN_MENU_, activeMenuInfo[0]);
                request.setAttribute(ACTIVE_SUB_MENU_, activeMenuInfo[1]);
            }
        }
        try{
            filterChain.doFilter(request, response);
        }catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public static void cleanActiveMenu() {
        HttpServletHelper.setCookie(getRequestResponseHolder().getResponse(), ACTIVE_MENU_COOKIE_KEY, null, 0, "/");
        getRequestResponseHolder().getRequest().setAttribute(ACTIVE_MAIN_MENU_, null);
        getRequestResponseHolder().getRequest().setAttribute(ACTIVE_SUB_MENU_, null);
    }
}
