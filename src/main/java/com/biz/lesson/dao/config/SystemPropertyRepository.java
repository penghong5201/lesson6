package com.biz.lesson.dao.config;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.config.SystemProperty;


@Repository
public interface SystemPropertyRepository extends CommonJpaRepository<SystemProperty, String>, SystemPropertyDao{

    List<SystemProperty> findByOrderByNameAsc();


}
