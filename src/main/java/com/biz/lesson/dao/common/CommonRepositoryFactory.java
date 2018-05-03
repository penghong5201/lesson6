package com.biz.lesson.dao.common;

import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

public class CommonRepositoryFactory extends JpaRepositoryFactory {

	public CommonRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
	}


	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return CommonJpaRepositoryBean.class;
	}
}
