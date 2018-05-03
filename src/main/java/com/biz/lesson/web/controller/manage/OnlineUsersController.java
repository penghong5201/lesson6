package com.biz.lesson.web.controller.manage;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserHelper;
import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/onlineUser")
@Secured("ROLE_ONLINE_USER")
public class OnlineUsersController extends BaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserHelper userHelper;

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_MANAGE_ONLINE_USER_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/onlineUsers/list");
        List onlineUsers = userHelper.getOnlineUsers();
        if(logger.isDebugEnabled()){
            logger.debug("onlineUsers size:{}",CollectionUtils.size(onlineUsers));
        }
        modelAndView.addObject("onlineUsers", onlineUsers);
        modelAndView.addObject("userRequestMap", userHelper.getUserRequestMap());
        return modelAndView;
    }

    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MANAGE_ONLINE_USER_DETAIL')")
    public ModelAndView detail(@RequestParam("id") String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/onlineUsers/detail");
        modelAndView.addObject("user", userManager.getUser(id));
        return modelAndView;
    }
}
