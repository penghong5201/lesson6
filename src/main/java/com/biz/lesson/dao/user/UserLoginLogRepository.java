package com.biz.lesson.dao.user;

import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.user.UserLoginLog;


@Repository
public interface UserLoginLogRepository extends CommonJpaRepository<UserLoginLog, String>, UserLoginLogDao{


}
