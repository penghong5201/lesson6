package com.biz.lesson.dao.user;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.user.Role;


@Repository
public interface RoleRepository extends CommonJpaRepository<Role, Long>, RoleDao{


}
