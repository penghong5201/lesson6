package com.biz.lesson.vo.user;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import java.sql.Date;

/**
 * Created by zhangjian on 17/3/20.
 */
public class MyInfoVo implements java.io.Serializable  {

    private static final long serialVersionUID = 8577886703288924377L;

    @Length(max = 50)
    private String name;

    @Length(max = 50)
    private String nameEn;

    @Length(max = 1)
    private String gender;

    @Length(max = 50)
    private String phone;

    @Length(max = 50)
    private String email;

    @Length(max = 100)
    private String skin;

    private Date birthday;

    @Length(max = 50)
    private String fax;

    @Length(max = 50)
    private String smtpServer;

    @Length(max = 100)
    private String msn;

    @Length(max = 50)
    private String mobile;

    @Length(max = 50)
    private String timeZone = "Asia/Shanghai";

    @Length(max = 100)
    private String emailPassword;

    @Length(max = 255)
    private String address;

    @Column(length = 255)
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
}
