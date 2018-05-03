package com.biz.lesson.dao.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.Resource;


public class ResourceRepositoryImpl extends CommonJpaRepositoryBean<Resource, Long> implements
		ResourceDao {

	@Autowired
	public ResourceRepositoryImpl(EntityManager em) {
		super(Resource.class, em);
	}
	
	
}
