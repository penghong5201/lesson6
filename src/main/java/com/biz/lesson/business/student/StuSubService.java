package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StuSubRepository;
import com.biz.lesson.model.student.StuSub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuSubService extends BaseService {
    //日志文件管理
    private static final Logger logger = LoggerFactory.getLogger(StuSubService.class);

    @Autowired(required = false)
    private StuSubRepository stuSubRepository;

    //根据学生id查询
    public List<StuSub> find(String id){
        return stuSubRepository.findByStudent_id(id);
    }
    //根据课程id查询
    public List<StuSub> findBysubId(String id){
        return stuSubRepository.findBySubject_id(id);
    }
    //根据课程id和学生id查询
    public List<StuSub> findBytwo(String student_id,String subject_id){
        return stuSubRepository.findByStudent_idAndSubject_id(student_id,subject_id);
    }

    //删除
    public void delete(StuSub stuSub){
        stuSubRepository.delete(stuSub);
    }
    public List<StuSub> list() {

        return stuSubRepository.findAll();
    }
    public StuSub create(StuSub stuSub){
        return stuSubRepository.save(stuSub);
    }

    public StuSub update(StuSub stuSub) {
        return stuSubRepository.save(stuSub);
    }

}
