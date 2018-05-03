package com.biz.lesson.business.user;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.user.MainMenuRepository;
import com.biz.lesson.dao.user.MenuItemRepository;
import com.biz.lesson.dao.user.ResourceRepository;
import com.biz.lesson.dao.user.RoleRepository;
import com.biz.lesson.dao.user.UserLoginLogRepository;
import com.biz.lesson.dao.user.UserRepository;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.CacheEvent;
import com.biz.lesson.model.user.MainMenu;
import com.biz.lesson.model.user.Menu;
import com.biz.lesson.model.user.MenuItem;
import com.biz.lesson.model.user.Resource;
import com.biz.lesson.model.user.Role;
import com.biz.lesson.model.user.User;
import com.biz.lesson.model.user.UserLoginLog;
import com.biz.lesson.vo.user.SearchLoginLog;

/**
 * 用户服务类
 */
@Transactional
public class UserManager extends BaseService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private MainMenuRepository mainMenuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserLoginLogRepository loginLogRepository;

    
    public void createUser(User admin, User createBy,boolean setCompanyId) {
        admin.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        admin.setCreateBy(createBy.getUserId());
        userRepository.save(admin);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }
    
    public void createUser(User admin, String creator,boolean setCompanyId) {
    	User user = getUser(creator);
    	this.createUser(admin, user,setCompanyId);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public boolean checkLogin(String userName, String password) {
        User admin = userRepository.findOne(userName);
        return admin != null && StringUtils.equalsIgnoreCase(admin.getPassword(), password);
    }

    public User getUser(String id) {
        return userRepository.findOne(id);
    }

    public List<User> listEnableUsers() {
        return userRepository.findByStatusAndUserTypeOrderByNameAsc(true,User.UT_ADMIN);
    }

    public List<User> listDisableUsers() {
        return userRepository.findByStatus(false);
    }

    public void deleteUser(String id) {
        userRepository.disableUser(id);
    }

    public void deleteUser(User admin) {
        userRepository.disableUser(admin.getUserId());
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public void updateUser(User admin) {
        userRepository.save(admin);
        publishEvent(new CacheEvent(this,CacheEventType.USER));
    }

    public boolean checkUserExists(String id) {
        return userRepository.exists(id);
    }

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        return roleRepository.findOne(id);
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public List<MainMenu> listAllMainMenu() {
        return mainMenuRepository.findByOrderByCodeAscNameAsc();
    }

    public MainMenu getMainMenu(Long id) {
        return mainMenuRepository.findOne(id);
    }

    public MainMenu saveMainMenu(MainMenu mainMenu) {
        return mainMenuRepository.save(mainMenu);
    }

    public void deleteMainMenu(Long id) {
        mainMenuRepository.delete(id);
    }

    public void updateMainMenu(MainMenu mainMenu) {
        mainMenuRepository.save(mainMenu);
    }

    public void deleteMainMenu(MainMenu mainMenu) {
        mainMenuRepository.delete(mainMenu);
    }

    public List<MenuItem> listMenuItemByMainMenuId(Long mainMenuId) {
        return menuItemRepository.findByMainMenu_IdOrderByCodeAsc(mainMenuId);
    }

    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }

    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.delete(id);
    }

    public void deleteMenuItem(MenuItem menuItem) {
        menuItemRepository.delete(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public Resource getResource(Long id) {
        return resourceRepository.findOne(id);
    }

    public void createResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        resourceRepository.delete(id);
    }

    public void deleteResource(Resource resource) {
        resourceRepository.delete(resource);
    }

    public void updateResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public List<Menu> initUserMenu(User admin) {
        List<Menu> result = new ArrayList<Menu>();
        List<Role> roles = admin.getRoles();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        for (Role role : roles) {
            menuItems.addAll(role.getMenuItems());
        }

        for (MainMenu mainMenu : this.listAllMainMenu()) {
            List<Menu> children = new ArrayList<Menu>();
            for (int j = 0; j < mainMenu.getMenuItems().size(); j++) {
                MenuItem menuItem = mainMenu.getMenuItems().get(j);
                if (menuItems.contains(menuItem)) {
                    children.add(new Menu(menuItem.getName(), menuItem.getNameEn(), menuItem.getLink(), menuItem.getIcon()));
                }
            }
            if (!children.isEmpty()) {
                result.add(new Menu(mainMenu.getName(), mainMenu.getNameEn(), "#", mainMenu.getIcon(), children));
            }
        }
        return result;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = userRepository.findOne(username);
        if (user != null) {
            Hibernate.initialize(user.getRoles());
            user.setMenus(this.initUserMenu(user));
            logger.info("load user from db;");
        } else {
            throw new UsernameNotFoundException("username:" + username + " is not exists");
        }
        return user;
    }

    public void updateItemParentId(Long parentId, Long itemId) {
        mainMenuRepository.updateItemParentId(parentId, itemId);

    }

    public void saveUserLoginLog(UserLoginLog userLoginLog) {
        loginLogRepository.save(userLoginLog);
    }

    public Page<UserLoginLog> searchUserLoginLog(Pageable pageable, SearchLoginLog condition) {
        return loginLogRepository.search(pageable, condition);
    }

    public List<User> getUsers(List<String> ids) {
        return this.userRepository.findByUserIdIn(ids);
    }
}
