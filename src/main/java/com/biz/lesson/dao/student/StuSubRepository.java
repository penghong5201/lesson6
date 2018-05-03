package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.StuSub;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StuSubRepository extends CommonJpaRepository<StuSub, String>, StuSubDao {
   //根据学生id查询
   List<StuSub> findByStudent_id(String id);
   //根据课程id查询
   List<StuSub> findBySubject_id(String id);
   //void deleteBystudent_id(String id);
   //根据学生id和课程id精确查询
   List<StuSub> findByStudent_idAndSubject_id(String student_id,String subject_id);
}
