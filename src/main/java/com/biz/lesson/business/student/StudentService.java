package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.student.Student;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
//容器管理
@Service
public class StudentService extends BaseService {
    //日志文件管理
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired(required = false)
    private StudentRepository studentRepository;

    //list方法 Studentcontroller
    public List<Student> list() {

        return studentRepository.findAll();
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student get(String id) {
        return studentRepository.findOne(id);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Student student) {
       studentRepository.delete(student.getId());
    }

    public List<Student> search(String studentid,String name,String stime,String etime){
        return studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(studentid)){
                    predicates.add(criteriaBuilder.like(root.get("studentid").as(String.class),studentid+"%"));
                }
                if(StringUtils.isNotBlank(name)){
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
                }
                if(StringUtils.isNotBlank(stime)){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(String.class),stime));
                }
                if(StringUtils.isNotBlank(etime)){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(String.class),etime));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
