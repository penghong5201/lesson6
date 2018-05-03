package com.biz.lesson.business.init;

import static com.google.common.collect.Lists.newArrayList;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.lesson.dao.config.SystemPropertyRepository;
import com.biz.lesson.dao.user.MainMenuRepository;
import com.biz.lesson.dao.user.MenuItemRepository;
import com.biz.lesson.dao.user.ResourceRepository;
import com.biz.lesson.dao.user.RoleRepository;
import com.biz.lesson.dao.user.UserRepository;
import com.biz.lesson.model.config.SystemProperty;
import com.biz.lesson.model.user.MainMenu;
import com.biz.lesson.model.user.MenuItem;
import com.biz.lesson.model.user.Resource;
import com.biz.lesson.model.user.Role;
import com.biz.lesson.model.user.User;

/**
 * 项目初始化数据使用
 */
@Service
public class InitManager {

    private static final Logger logger = LoggerFactory.getLogger(InitManager.class);

    @Autowired private MainMenuRepository mainMenuRepository;

    @Autowired private MenuItemRepository menuItemRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private ResourceRepository resourceRepository;
    
    @Autowired private SystemPropertyRepository propertyRepository;

    @PostConstruct
    public void init(){
        if(userRepository.count() == 0){
            logger.info("开始初始化系统基础数据...");

            //User
            User user = new User();
            user.setUserId("admin");
            user.setName("超级管理员");
            user.setNameEn("SuperAdmin");
            user.setPassword("ceb4f32325eda6142bd65215f4c0f371");
            user = userRepository.save(user);

            //Main menu
            MainMenu mainMenu = new MainMenu();
            mainMenu.setName("系统信息");
            mainMenu.setNameEn("System Info");
            mainMenu.setIcon("fa fa-cogs");
            mainMenu.setCode(999);
            mainMenu = mainMenuRepository.save(mainMenu);

            //Menu Item
            MenuItem menuItemOfUser = buildMenuItem("用户管理", "User Management", "ROLE_USER;OPT_USER_LIST", 1, "/manage/user/list.do", mainMenu);
            menuItemOfUser = menuItemRepository.save(menuItemOfUser);
            MenuItem menuItemOfMainMenu = buildMenuItem("菜单管理", "Main Menu", "ROLE_MAINMENU;OPT_MAINMENU_LIST;ROLE_MENUITEM;ROLE_RESOURCE", 2, "/manage/mainMenus.do", mainMenu);
            menuItemOfMainMenu = menuItemRepository.save(menuItemOfMainMenu);
            MenuItem menuItemOfRole = buildMenuItem("角色管理", "Role Management", "ROLE_ROLE;OPT_ROLE_LIST", 3, "/manage/roles.do", mainMenu);
            menuItemOfRole = menuItemRepository.save(menuItemOfRole);
            MenuItem menuItemConfig = buildMenuItem("配置管理", "Config", "ROLE_CONFIG,OPT_CONFIG_LIST,OPT_CONFIG_DELETE,OPT_CONFIG_ADD,OPT_CONFIG_EDIT", 4, "/manage/config/list.do", mainMenu);
            menuItemConfig = menuItemRepository.save(menuItemConfig);
            MenuItem menuItemAccessLog = buildMenuItem("访问日志", "AccessLog", "OPT_ACCESSLOG_SEARCH", 4, "/manage/accesslog/search.do", mainMenu);
            menuItemAccessLog = menuItemRepository.save(menuItemAccessLog);
            
            
            //Resource
            Resource manageUser = builtResource("用户管理", "User Management", "OPT_USER_ADD;OPT_USER_EDIT;OPT_USER_DELETE;OPT_USER_RESET;OPT_USER_DETAIL",menuItemOfUser);
            manageUser = resourceRepository.save(manageUser);
            Resource checkUserDetail = builtResource("查看用户", "User Read", "OPT_USER_DETAIL",menuItemOfUser);
            checkUserDetail = resourceRepository.save(checkUserDetail);

            Resource manageMenuItem = builtResource("菜单管理", "Menu Management", "OPT_MAINMENU_ADD;OPT_MAINMENU_EDIT;OPT_MAINMENU_DELETE;OPT_MAINMENU_DETAIL;OPT_MENUITEM_ADD;OPT_MENUITEM_EDIT;OPT_MENUITEM_DELETE;OPT_MENUITEM_DETAIL;OPT_RESOURCE_ADD;OPT_RESOURCE_EDIT;OPT_RESOURCE_DELETE",menuItemOfMainMenu);
            manageMenuItem = resourceRepository.save(manageMenuItem);
            Resource checkMainMenu = builtResource("菜单查看", "Main Menu Management", "OPT_MAINMENU_DETAIL;OPT_MENUITEM_DETAIL",menuItemOfMainMenu);
            checkMainMenu = resourceRepository.save(checkMainMenu);

            Resource manageRole = builtResource("角色管理", "Role Management", "OPT_ROLE_ADD;OPT_ROLE_EDIT;OPT_ROLE_DELETE;OPT_ROLE_DETAIL",menuItemOfRole);
            manageRole = resourceRepository.save(manageRole);
            
            Resource checkRoleDetail = builtResource("角色查看", "Role Read", "OPT_ROLE_DETAIL",menuItemOfRole);
            checkRoleDetail = resourceRepository.save(checkRoleDetail);

            Resource manageConfig = builtResource("配置管理", "Config Management", "ROLE_CONFIG,OPT_CONFIG_LIST,OPT_CONFIG_DELETE,OPT_CONFIG_ADD,OPT_CONFIG_EDIT",menuItemConfig);
            manageConfig = resourceRepository.save(manageConfig);
            
            
            //Role
            Role role = new Role();
            role.setName("超级管理员");
            role.setNameEn("Super Admin");
            role.setDescription("拥有菜单管理，角色管理，用户管理权限");
            role.setMenuItems(newArrayList(menuItemOfUser, menuItemOfMainMenu, menuItemOfRole,menuItemConfig,menuItemAccessLog));
            role.setResources(newArrayList(manageUser, checkMainMenu, manageMenuItem, checkUserDetail, manageRole, checkRoleDetail,manageConfig));
            role = roleRepository.save(role);

            user.setRoles(newArrayList(role));
            userRepository.save(user);
           
            
            propertyRepository.save(new SystemProperty("access_log_save_sync", "true", true, "是否同步存储访问日志"));
            propertyRepository.save(new SystemProperty("company_name", "博智信息", true, "界面中使用的公司名称"));
            propertyRepository.save(new SystemProperty("domain.cn", "lesson.biz-united.com.cn", true, "访问此系统使用的域名"));
            propertyRepository.save(new SystemProperty("system_logo", "", true, "系统LOGO"));
            propertyRepository.save(new SystemProperty("system_title", "LESSON SIX", true, "系统名称"));
            
            logger.info("完成初始化系统基础数据...");
        }
    }
    
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    private MenuItem buildMenuItem(String name, String nameEn, String symbol, Integer code, String link, MainMenu mainMenu) {
        MenuItem menuItemOfRole = new MenuItem();
        menuItemOfRole.setName(name);
        menuItemOfRole.setNameEn(nameEn);
        menuItemOfRole.setSymbol(symbol);
        menuItemOfRole.setCode(code);
        menuItemOfRole.setLink(link);
        menuItemOfRole.setMainMenu(mainMenu);
        return menuItemOfRole;
    }

    private Resource builtResource(String name, String nameEn, String symbol, MenuItem menuItemOfUser) {
        Resource resource = new Resource();
        resource.setName(name);
        resource.setNameEn(nameEn);
        resource.setSymbol(symbol);
        resource.setMenuItem(menuItemOfUser);
        return resource;
    }
}
