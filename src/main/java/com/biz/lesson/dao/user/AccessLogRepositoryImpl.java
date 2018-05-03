package com.biz.lesson.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.vo.user.AccessLogSearchVo;
import com.google.common.collect.Lists;

@Repository("logRepositoryImpl")
public class AccessLogRepositoryImpl extends CommonJpaRepositoryBean<AccessLogPo, Long> implements AccessLogDao {

	@Autowired
	public AccessLogRepositoryImpl(EntityManager em) {
		super(AccessLogPo.class, em);
	}

	public Specification<AccessLogPo> buildSearchSpecification(final AccessLogSearchVo reqVo) {
		return (root, query, cb) -> {
			List<Predicate> predicates = Lists.newArrayList();

			if (StringUtils.isNotBlank(reqVo.getUser())) {
				predicates.add(cb.equal(root.get("user"), reqVo.getUser()));
			}

			if (StringUtils.isNotBlank(reqVo.getKeywords())) {
				predicates.add(cb.like(root.get("requestParams"), "%" + reqVo.getKeywords() + "%"));
			}

			if (StringUtils.isNotBlank(reqVo.getUri())) {
				predicates.add(cb.like(root.get("uri"), "%" + reqVo.getUri() + "%"));
			}

			if (reqVo.getStartTime() != null) {
				predicates.add(cb.greaterThan(root.get("timestamp"), reqVo.getStartTime()));
			}

			if (reqVo.getEndTime() != null) {
				predicates.add(cb.lessThan(root.get("timestamp"), reqVo.getEndTime()));
			}
			// 将所有条件用 and 联合起来
			if (predicates.size() > 0) {
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			} else {
				return null;
			}
		};
	}

	public Page<AccessLogPo> search(AccessLogSearchVo reqVo, Pageable pageable) {
		 return super.findAll(buildSearchSpecification(reqVo),pageable);
	}
}