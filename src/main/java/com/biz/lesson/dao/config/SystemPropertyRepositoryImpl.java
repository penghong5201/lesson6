package com.biz.lesson.dao.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.config.SystemProperty;


public class SystemPropertyRepositoryImpl extends CommonJpaRepositoryBean<SystemProperty, String> implements SystemPropertyDao{

	@Autowired
	public SystemPropertyRepositoryImpl(EntityManager em) {
		super(SystemProperty.class, em);
	}
	
	
}
