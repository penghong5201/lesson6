package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.student.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//容器管理
@Service
public class GradeService extends BaseService {
    //日志文件管理
    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);

    @Autowired(required = false)
    private GradeRepository gradeRepository;

    //list方法
    public List<Grade> list() {
        return gradeRepository.findAll();
    }

    public Grade create(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade get(String id) {
        return gradeRepository.findOne(id);
    }

    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void delete(Grade grade) {
        gradeRepository.delete(grade.getId());
    }
}
