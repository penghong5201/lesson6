package com.biz.lesson.vo.student;

import com.biz.lesson.business.student.StuSubService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/*
 *学生
 */

public class StudentVo{
    private String id;
    private String studentid;
    private String name;
    private Date birthday;
    private String sex;
    private int count;

    @Autowired
    private StuSubService stuSubService;

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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public int getCount() {
        /*List<StuSub> stuSubs = stuSubService.find(this.getId());
        count = stuSubs.size();*/
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
