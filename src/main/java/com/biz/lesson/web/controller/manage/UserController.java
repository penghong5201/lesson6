package com.biz.lesson.web.controller.manage;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.user.Role;
import com.biz.lesson.model.user.User;
import com.biz.lesson.vo.user.UserVo;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("manage/user")
public class UserController extends BaseController {

    @Autowired
    @Qualifier("userDetailsManager")
    private UserManager manager;


    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/admin/list");
        List<User> admins = manager.listEnableUsers();
        modelAndView.addObject("admins", admins);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/admin/add");
        modelAndView.addObject("cmd", "add");
        modelAndView.addObject("roles", manager.listAllRoles());
        
        User user = new User();
        modelAndView.addObject("admin", user);
        
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView edit(String userId, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/admin/add");

        User user = manager.getUser(userId);
        BusinessAsserts.exists(user, userId);

        modelAndView.addObject("admin", user);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("roles", manager.listAllRoles());
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/admin/detail");

        User user = manager.getUser(userId);
        BusinessAsserts.exists(user, userId);

        modelAndView.addObject("admin", user);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView save_add(UserVo vo, String pwd, BindingResult result, HttpServletRequest request) throws Exception {
        User user = new User();
        copyProperties(vo, user);

        List<Role> roles = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(vo.getRoles())){
            for(String roleId : vo.getRoles()){
                roles.add(manager.getRole(Long.valueOf(roleId)));
            }
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encodePassword(pwd, user.getUserId()));
        User creator = DataStorageThreadLocalHolder.getUser();
        manager.createUser(user, creator,true);
        return referer(request);
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView save_edit(UserVo vo, String pwd, BindingResult result, HttpServletRequest request) throws Exception {
        User user = manager.getUser(vo.getUserId());
        BusinessAsserts.exists(user, vo.getUserId());

        copyProperties(vo, user);
        if(user.getRoles()!=null){
        	user.getRoles().clear();
        }
        if (CollectionUtils.isNotEmpty(vo.getRoles())){
            for(String roleId:vo.getRoles()){
                user.getRoles().add(manager.getRole(Long.valueOf(roleId)));
            }
        }
        manager.updateUser(user);
        return referer(request);
    }

    @RequestMapping(value = "delete", method = POST)
    @PreAuthorize("hasAuthority('OPT_STUDENT_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") String userId) {
        User admin = manager.getUser(userId);
        try {
            manager.deleteUser(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "check", method = GET)
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    @ResponseBody
    public Boolean check(@RequestParam("userId") String userId) {
        return manager.checkUserExists(userId);
    }

    @RequestMapping(value = "resetPassword")
    @PreAuthorize("hasAuthority('OPT_USER_RESET')")
    public ModelAndView resetPassword(String userId) {
        ModelAndView modelAndView = new ModelAndView("manage/admin/reset");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }



    @RequestMapping(value = "save_resetPassword")
    @PreAuthorize("hasAuthority('OPT_USER_RESET')")
    @ResponseBody
    public Boolean saveResetPassword(String userId, String pwd) {
        try {
            User user = manager.getUser(userId);
            if (user != null) {
                user.setPassword(passwordEncoder.encodePassword(pwd, user.getUserId()));
                manager.updateUser(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
