package com.biz.lesson.dao.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class CommonJpaRepositoryBean<T, ID extends java.io.Serializable> extends SimpleJpaRepository<T, ID>  implements CommonJpaRepository<T, ID>,
JpaSpecificationExecutor<T> {

	private EntityManager entityManager;
	private JpaEntityInformation<T, ?> entityInformation;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public CommonJpaRepositoryBean(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	public CommonJpaRepositoryBean(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
	}


	public Map<ID, T> mget(Collection<ID> ids) {
		
		return toMap(findAll(ids));
	}

	private Map<ID, T> toMap(List<T> list) {
		Map<ID, T> result = new LinkedHashMap<ID, T>();
		for (T t : list) {
			if (t != null) {
				result.put((ID)entityInformation.getId(t), t);
			}
		}
		return result;
	}

    public T persist(T t) {
        entityManager.persist(t);
        return t;
    }
    
    public List<T> findAll(ID[] ids) {
        if (ids != null && ids.length > 0) {
            List<ID> idList = Arrays.asList(ids);
            return super.findAll(idList);
        } else
            return null;
    }

}
