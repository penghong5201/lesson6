package com.biz.lesson.test.dao.admin;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.vo.user.SearchLoginLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class LoginLogTest {

	@Autowired
	private UserManager userManager;

	
    @Before 
    public void setUp() throws Exception {

    }

    @After 
    public void clean() throws Exception {
    
    }
    
    @Test
    public void testSearch() throws Exception {
    	SearchLoginLog condition = new SearchLoginLog() {
			
			@Override
			public String getUserId() {
				return "gst";
			}
			
			@Override
			public Date getToDate() {
				return null;
			}
			
			@Override
			public Date getFromDate() {
				return null;
			}
		};
		
		userManager.searchUserLoginLog(new PageRequest(1, 100), condition);
		
    }
    
    
  
}
