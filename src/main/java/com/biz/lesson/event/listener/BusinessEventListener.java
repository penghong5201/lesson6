package com.biz.lesson.event.listener;

import org.springframework.context.ApplicationListener;

import com.biz.lesson.event.event.BusinessEvent;

/**
 * 事件监听器基类
 */
public abstract class BusinessEventListener implements  ApplicationListener<BusinessEvent> {

    
    @Override
    public void onApplicationEvent(BusinessEvent event) {
        handleEvent(event);
    }

    protected abstract void handleEvent(BusinessEvent event);


}
