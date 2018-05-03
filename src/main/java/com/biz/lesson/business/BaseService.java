package com.biz.lesson.business;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.biz.lesson.event.event.BusinessEvent;
import com.biz.lesson.exception.CopyPropertiesException;
import com.biz.lesson.util.BeanUtils;

/**
 * 基础服务类
 */
public class BaseService implements ApplicationEventPublisherAware {

    
    private ApplicationEventPublisher applicationEventPublisher; 


    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void publishEvent(BusinessEvent event) {
        applicationEventPublisher.publishEvent(event);
//        if(event instanceof CacheEvent){
//        	CacheEvent ce = (CacheEvent) event;
//        	syncCacheEventRepository.save(SyncCacheEvent.fromCacheEvent(ce));
//        }
    }

    public void copyProperties(Object vo,Object po) {
        try{
            BeanUtils.copyProperties(vo, po);
        }catch(Exception e){
            throw new CopyPropertiesException(e);
        }
    }
    
}
