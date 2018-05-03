package com.biz.lesson.vo.student;

/*
 *学生
 */

import com.biz.lesson.model.student.Student;

import java.util.List;

public class GradeVo {
    private String id;

    private String name;

    private List<Student> students;

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
}
