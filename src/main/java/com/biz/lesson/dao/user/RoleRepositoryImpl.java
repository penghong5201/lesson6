package com.biz.lesson.dao.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.Role;


public class RoleRepositoryImpl extends CommonJpaRepositoryBean<Role, String> implements RoleDao{

	@Autowired
	public RoleRepositoryImpl(EntityManager em) {
		super(Role.class, em);
	}
	
	
}
