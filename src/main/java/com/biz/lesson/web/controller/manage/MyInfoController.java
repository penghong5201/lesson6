package com.biz.lesson.web.controller.manage;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.User;
import com.biz.lesson.vo.user.MyInfoVo;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;

@Controller
@RequestMapping("/userInfo")
public class MyInfoController extends BaseController {

    @Autowired
    private UserManager userManager;

    @RequestMapping("/detail")
    public ModelAndView detail() throws Exception {
        ModelAndView modelAndView = new ModelAndView("myInfo");
        modelAndView.addObject("my", userManager.getUser(DataStorageThreadLocalHolder.getUser().getId()));
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(MyInfoVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
            vo.setBirthday(Date.valueOf(request.getParameter("birthday")));
        }
        if (logger.isDebugEnabled()) {
            logger.debug(vo.toString());
        }

        User po = userManager.getUser(DataStorageThreadLocalHolder.getUser().getId());
        copyProperties(vo, po);
        userManager.updateUser(po);
        return new ModelAndView("redirect:/welcome.do");
    }
    
    @RequestMapping("/saveSkin")
    @ResponseBody
    public boolean saveSkin(String skin) throws Exception {
        User user = DataStorageThreadLocalHolder.getUser();
        if(user!=null){
            if(StringUtils.isNotEmpty(skin)){
                user.setSkin(skin);
            }else{
                user.setSkin("no-skin");
            }
            userManager.updateUser(user);
            return true;
        }
        return false;
    }

    @RequestMapping("/saveCheckNavbar")
    @ResponseBody
    public boolean saveCheckNavBar(String navbar) throws Exception {
        User user = DataStorageThreadLocalHolder.getUser();
        if(user!=null){
            if(StringUtils.isNotEmpty(navbar)){
                user.setNavbar(true);
            }else{
                user.setNavbar(false);
            }


            userManager.updateUser(user);
            return true;
        }
        return false;
    }

    @RequestMapping("/saveCheckSidebar")
    @ResponseBody
    public boolean saveCheckSideBar(String sidebar) throws Exception {
        User user = DataStorageThreadLocalHolder.getUser();
        if(user!=null){

            if(StringUtils.isNotEmpty(sidebar)){
                user.setSidebar(true);
            }else{
                user.setSidebar(false);
            }

            userManager.updateUser(user);
            return true;
        }
        return false;
    }



    @RequestMapping("/saveServiceType")
    @ResponseBody
    public boolean saveServiceType(String filter) throws Exception {
        User user = DataStorageThreadLocalHolder.getUser();
        if(user!=null){
            user.setServiceType(filter);
            userManager.updateUser(user);
            return true;
        }
        return false;
    }

}
