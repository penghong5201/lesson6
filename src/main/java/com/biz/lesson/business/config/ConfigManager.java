package com.biz.lesson.business.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.config.SystemPropertyRepository;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.model.config.SystemProperty;

/**
 *  配置服务类
 */
@Service
public class ConfigManager extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

	@Autowired
	private SystemPropertyRepository propertyRepository;

	public List<SystemProperty> listProperties() {
		return propertyRepository.findByOrderByNameAsc();
	}

	public SystemProperty getSystemProperty(String id) {
		return propertyRepository.findOne(id);
	}

	public SystemProperty saveSystemProperty(SystemProperty systemProperty) {
		SystemProperty result = propertyRepository.save(systemProperty);
		publishEvent(new CacheEvent(this, CacheEventType.CONFIG));
		return result;
	}

	public void deleteSystemProperty(String id) {
		propertyRepository.delete(id);
		super.publishEvent(new CacheEvent(this, CacheEventType.CONFIG));
	}


}
