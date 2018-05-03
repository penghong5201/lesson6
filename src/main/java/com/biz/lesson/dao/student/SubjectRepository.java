package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Subject;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject, String>, SubjectDao {
}
