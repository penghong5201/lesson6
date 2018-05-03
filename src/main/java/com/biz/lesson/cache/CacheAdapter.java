package com.biz.lesson.cache;

import javax.servlet.ServletContext;
/**
 * 缓存适配器接口
 */
public interface CacheAdapter {
    
    public void intDataToApplication(ServletContext application);

}
