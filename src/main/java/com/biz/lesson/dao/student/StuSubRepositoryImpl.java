package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.student.StuSub;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class StuSubRepositoryImpl extends CommonJpaRepositoryBean<StuSub, String> implements StuSubDao{

	@Autowired
	public StuSubRepositoryImpl(EntityManager em) {
		super(StuSub.class, em);
	}
	
	
}
