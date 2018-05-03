package com.biz.lesson.test.dao.admin;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.dao.user.MainMenuRepository;
import com.biz.lesson.dao.user.MenuItemRepository;
import com.biz.lesson.model.user.MainMenu;
import com.biz.lesson.model.user.MenuItem;
import com.biz.lesson.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class MenuItemTest {

	@Autowired(required=true)
	private UserManager userManager;
	
	@Autowired 
	private MainMenuRepository mainMenuRepository;
	
	@Autowired 
	private MenuItemRepository menuItemRepository;

	
    @Before 
    public void setUp() throws Exception {
    	
    }

    @After 
    public void clean() throws Exception {
    	mainMenuRepository.deleteAll();
    	menuItemRepository.deleteAll();
    }
    
    @Test 
    public void testAddMainMenu() throws Exception {
    	MainMenu m = new MainMenu();
    	m.setName("菜单1");
    	m.setNameEn("menu");
    	m.setCode(1);
    	m.setDescription("this is description");
    	m.setCompanyType("broker");
    	
    	MainMenu saved = userManager.saveMainMenu(m);
    	
    	System.out.println(JsonUtil.obj2Json(saved));
    	
    	Assert.isTrue(userManager.listAllMainMenu().size()>0);
    }
    
    @Test 
    public void testSaveMenuItem() throws Exception {
    	MainMenu m = new MainMenu();
    	m.setName("菜单1");
    	m.setNameEn("menu");
    	m.setCode(1);
    	m.setDescription("this is description");
    	m.setCompanyType("broker");
    	
    	
    	MenuItem item = new MenuItem();
    	item.setCode(1);
    	item.setDescription("this is menuItem1");
    	item.setLink("/login.do");
    	item.setName("menuitem1");
    	item.setNameEn("menuitem1");
    	item.setMainMenu(m);
    	
    	MenuItem saved = userManager.saveMenuItem(item);
    	
    	System.out.println(JsonUtil.obj2Json(saved));
    	
    	Assert.isTrue(userManager.listAllMainMenu().size()>0);
    	
    	
    	List<MenuItem> items = userManager.listMenuItemByMainMenuId(saved.getMainMenu().getId());
    	
    	System.out.println(JsonUtil.obj2Json(items));
    }

  
}
