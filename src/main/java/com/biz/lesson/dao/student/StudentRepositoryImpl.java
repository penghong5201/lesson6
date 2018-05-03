package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;

import com.biz.lesson.model.student.Student;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class StudentRepositoryImpl extends CommonJpaRepositoryBean<Student, String> implements StudentDao{

	@Autowired
	public StudentRepositoryImpl(EntityManager em) {
		super(Student.class, em);
	}
	
	
}
