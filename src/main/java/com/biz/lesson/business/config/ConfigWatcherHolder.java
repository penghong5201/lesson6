package com.biz.lesson.business.config;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.lesson.event.event.BusinessEvent;
import com.biz.lesson.event.event.ConfigChangedEvent;
import com.biz.lesson.event.listener.BusinessEventListener;
/**
 * 配置观察者
 */
@Component
public class ConfigWatcherHolder  extends BusinessEventListener{

	 private static final Logger logger = LoggerFactory.getLogger(ConfigWatcherHolder.class);

    @Autowired(required = false)
    private List<ConfigWatcher> watchers = null;

	public List<ConfigWatcher> getWatchers() {
		return watchers;
	}

	public void setWatchers(List<ConfigWatcher> watchers) {
		this.watchers = watchers;
	}
	
    @Override
    protected void handleEvent(BusinessEvent event) {
        if (event instanceof ConfigChangedEvent) {
            if (CollectionUtils.isNotEmpty(watchers)) {
                for (ConfigWatcher watcher : watchers) {
                    logger.info(" {} reload config", watcher.getClass().getCanonicalName());
                    watcher.reloadConfig();
                }
            }
        }
    }

}
