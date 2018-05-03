package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stu_subject")
public class Subject extends Persistent {

    /*@Id
    @Column(length = 20,nullable = false)
    private String id;*/

    @Column(length = 20,nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subject")
    private List<StuSub> stuSubs;

    @Formula("(select AVG(ss.score) from stu_sub ss where ss.subject_id=id)")
    private Integer avgscore;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "stu_sub",
            joinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "subject_id", "student_id" }) })
    private List<Student> students;*/

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

    public List<StuSub> getStuSubs() {
        return stuSubs;
    }

    public void setStuSubs(List<StuSub> stuSubs) {
        this.stuSubs = stuSubs;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }
}
