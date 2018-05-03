package com.biz.lesson.event.event;


import org.springframework.context.ApplicationEvent;

/**
 * 业务事件基类
 */
public class BusinessEvent extends ApplicationEvent{

    private static final long serialVersionUID = 4055436423052586529L;

    public BusinessEvent(Object source) {
		super(source);
	}

}
