package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.dao.user.MainMenuDao;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.user.MainMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends CommonJpaRepository<Student, String>, StudentDao {
}
