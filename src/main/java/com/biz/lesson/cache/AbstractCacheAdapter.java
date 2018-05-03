package com.biz.lesson.cache;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.BusinessEvent;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.event.listener.BusinessEventListener;

/**
 * 缓存适配器
 */
public abstract class AbstractCacheAdapter extends BusinessEventListener implements CacheAdapter,ApplicationEventPublisherAware {


    protected ServletContext application;
    protected CacheEventType[] careCacheEventTypes;
    
    private ApplicationEventPublisher applicationEventPublisher;
    
    protected abstract void refresh(CacheEventType cacheEventType);
    
    protected abstract void init();

    
    public ServletContext getApplication() {
        return application;
    }

    public void intDataToApplication(ServletContext application) {
        this.application = application;
        init();
    }

    @Override
    protected void handleEvent(BusinessEvent event) {
       if(event instanceof CacheEvent){
           CacheEvent cacheEvent = (CacheEvent) event;
           for(CacheEventType careCacheEventType :careCacheEventTypes){
               if(cacheEvent.getCacheEventType()==careCacheEventType){
                   refresh(careCacheEventType);
               }
           }
       }
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void publishEvent(BusinessEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
