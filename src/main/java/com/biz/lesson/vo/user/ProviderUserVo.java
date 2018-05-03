package com.biz.lesson.vo.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ProviderUserVo implements java.io.Serializable{
    

    private static final long serialVersionUID = 6958534705068922172L;

    @NotBlank
    @Length(max=20)
    private String userId;

    @NotBlank
    @Length(max=20)
    private String password;
    @NotBlank
    @Length(max=50)
    private String name;

    @NotBlank
    @Length(max=50)
    private String nameEn;
    
    @NotBlank
    private String company;
    
    @NotBlank
    private String userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    

}
