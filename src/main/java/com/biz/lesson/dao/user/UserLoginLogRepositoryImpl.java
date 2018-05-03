package com.biz.lesson.dao.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.user.User;
import com.biz.lesson.model.user.UserLoginLog;
import com.biz.lesson.util.DateUtil;
import com.biz.lesson.vo.user.SearchLoginLog;


public class UserLoginLogRepositoryImpl extends CommonJpaRepositoryBean<UserLoginLog, String> implements UserLoginLogDao{

	@Autowired
	public UserLoginLogRepositoryImpl(EntityManager em) {
		super(UserLoginLog.class, em);
	}
	
	private Specification<UserLoginLog> buildSearchSpecification(final SearchLoginLog condition) {
		return new Specification<UserLoginLog>() {
			public Predicate toPredicate(Root<UserLoginLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (StringUtils.isNotBlank(condition.getUserId())) {
					Path<User> user = root.get("user");
					predicates.add(cb.equal(user.get("userId"), condition.getUserId()));
				}
				if (condition.getFromDate() != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("loginTime").as(Timestamp.class),
							condition.getFromDate()));
				}
				if (condition.getToDate() != null) {
					predicates.add(
							cb.lessThan(root.get("loginTime").as(Timestamp.class), DateUtil.afterDay(condition.getToDate())));
				}
				// 将所有条件用 and 联合起来
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				} else {
					return null;
				}
			}
		};
	}
	
	public Page<UserLoginLog> search(Pageable pageable,SearchLoginLog condition){
		return findAll(buildSearchSpecification(condition), pageable);
	}
}
