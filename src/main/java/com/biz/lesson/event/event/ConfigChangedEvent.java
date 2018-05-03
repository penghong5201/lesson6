package com.biz.lesson.event.event;

/**
 * 配置变更事件
 */
public class ConfigChangedEvent extends BusinessEvent{

	private static final long serialVersionUID = -1380299934468716098L;

	public ConfigChangedEvent(Object source) {
		super(source);
	}

}
