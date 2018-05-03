package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.student.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//容器管理
@Service
public class SubjectService extends BaseService {
    //日志文件管理
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired(required = false)
    private SubjectRepository subjectRepository;

    //list方法
    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject get(String id) {
        return subjectRepository.findOne(id);
    }

    public Subject update(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject.getId());
    }
}
