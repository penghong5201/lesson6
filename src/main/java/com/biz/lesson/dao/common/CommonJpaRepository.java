package com.biz.lesson.dao.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {
	

	Map<ID, T> mget(Collection<ID> ids);

	/**
	 * 只有在 在对象Id 是赋值的时候才使用
	 * @param t
	 * @return
	 */
	T persist(T t);
	
    List<T> findAll(ID[] ids);
    
}

