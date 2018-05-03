package com.biz.lesson.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.vo.user.AccessLogSearchVo;

@NoRepositoryBean
public interface AccessLogDao {

	 Page<AccessLogPo> search(AccessLogSearchVo reqVo, Pageable pageable);
}
