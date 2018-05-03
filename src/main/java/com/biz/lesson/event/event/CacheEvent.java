package com.biz.lesson.event.event;

import com.biz.lesson.event.CacheEventType;
/**
 * 缓存更新时间
 */
public class CacheEvent extends BusinessEvent{

	private static final long serialVersionUID = -5884951242744570019L;
	
	private CacheEventType cacheEventType;
    
    public CacheEvent(Object source,CacheEventType type) {
        super(source);
        this.cacheEventType = type;
    }

    public CacheEventType getCacheEventType() {
        return cacheEventType;
    }

 
    
}
