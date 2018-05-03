package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Grade;
import org.springframework.stereotype.Repository;


@Repository
public interface GradeRepository extends CommonJpaRepository<Grade, String>, GradeDao {
}
