package com.biz.lesson.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biz.lesson.cache.CacheAdapter;
import com.biz.lesson.cache.CacheApapterHolder;
import com.biz.lesson.util.SpringContextUtil;

/**
 * 将 ServletContext 赋值给 CacheApapterHolder 让其向 ServletContext 填充全局缓存数据
 */
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent evt) {
        ServletContext application = evt.getServletContext();
        logger.info("contextInitialized");
        CacheApapterHolder cacheApapterHolder = SpringContextUtil.getBean(CacheApapterHolder.class);
        if(cacheApapterHolder!=null && CollectionUtils.isNotEmpty(cacheApapterHolder.getAdapters())){
            for(CacheAdapter adapter : cacheApapterHolder.getAdapters()){
                adapter.intDataToApplication(application);
            }
        }else{
            logger.info("this is no define CacheAdapter");
        }
    }

    public void contextDestroyed(ServletContextEvent evt) {

    }

}
