package com.biz.lesson.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.biz.lesson.model.user.User;

public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setServiceType("0");
    }

    @After
    public void clean() throws Exception {
        user = null;
    }

    @Test
    public void testUserServiceTypeMap0(){
        boolean isExist = user.getServiceTypeMap("0");
        Assert.isTrue(isExist);
    }


    @Test
    public void testUserServiceTypeMap1(){
        boolean isExist = user.getServiceTypeMap("1");
        Assert.isTrue(isExist);
    }

    @Test
    public void testUserServiceTypeMapKeyNull(){
        boolean isExist = user.getServiceTypeMap(null);
        Assert.isTrue(isExist);
    }
}
