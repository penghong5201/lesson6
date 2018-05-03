package com.biz.lesson.dao.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.User;


public class UserRepositoryImpl extends CommonJpaRepositoryBean<User, String> implements UserDao{

	@Autowired
	public UserRepositoryImpl(EntityManager em) {
		super(User.class, em);
	}
	
	
}
