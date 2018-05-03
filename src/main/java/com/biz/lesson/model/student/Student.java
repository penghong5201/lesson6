package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/*
 *学生
 */
@Entity
@Table(name="stu_student")
public class Student extends Persistent{
    /*@Id
    @Column(length = 20,nullable = false)
    private String id;*/

    @Column(length = 20,nullable = false)
    private String studentid;

    @Column(length = 40, nullable = false)
    private String name;

    @Column
    private Date birthday;

    @Column
    private String sex;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    /*@Formula("(select count(*) from stu_sub ss where ss.student_id=id)")*/
    @Transient
    private int count;

    @Formula("(select AVG(ss.score) from stu_sub ss where ss.student_id=id)")
    private Integer avgscore;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
    private List<StuSub> stuSubs;
   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "stu_sub",
            joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "student_id", "subject_id" }) })
    private List<Subject> subjects;*/


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<StuSub> getStuSubs() {
        return stuSubs;
    }

    public void setStuSubs(List<StuSub> stuSubs) {
        this.stuSubs = stuSubs;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }
}
