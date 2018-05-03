package com.biz.lesson.vo.student;

/*
 *学生
 */

import com.biz.lesson.model.student.StuSub;

import java.util.List;

public class SubjectVo {
    private String id;

    private String name;

    private List<StuSub> stuSubs;

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
}
