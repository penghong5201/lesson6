package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stu_grade")
public class Grade extends Persistent{
    /*@Id
    @Column(length = 20,nullable = false)
    private String id;*/

    @Column(length = 20,nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Student> students;
    @Formula("(select count(s.grade_id) from stu_student s where s.grade_id=id)")
    private int count;

    @Formula("(select AVG(ss.score) from stu_sub ss where ss.student_id in(select stu.id from stu_student stu where stu.grade_id=id))")
    private Integer avgscore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
