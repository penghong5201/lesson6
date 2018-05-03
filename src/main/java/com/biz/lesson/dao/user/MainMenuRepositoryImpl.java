package com.biz.lesson.dao.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.MainMenu;


public class MainMenuRepositoryImpl extends CommonJpaRepositoryBean<MainMenu, String> implements MainMenuDao{

	@Autowired
	public MainMenuRepositoryImpl(EntityManager em) {
		super(MainMenu.class, em);
	}
	
	
}
