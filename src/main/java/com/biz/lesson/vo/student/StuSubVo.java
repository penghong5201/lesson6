package com.biz.lesson.vo.student;

/*
 *学生
 */

import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;

public class StuSubVo {
    private String id;

    private Student student;

    private Subject subject;

    private String score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
