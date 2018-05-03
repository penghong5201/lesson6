package com.biz.lesson.dao.user;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.user.Resource;

@Repository
public interface ResourceRepository extends CommonJpaRepository<Resource, Long>, ResourceDao{



}
