package com.biz.lesson.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.biz.lesson.model.user.UserLoginLog;
import com.biz.lesson.vo.user.SearchLoginLog;

public interface UserLoginLogDao {

	public Page<UserLoginLog> search(Pageable pageable,SearchLoginLog condition);
}
