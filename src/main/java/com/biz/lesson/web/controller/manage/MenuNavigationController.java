package com.biz.lesson.web.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/navigation")
public class MenuNavigationController  extends BaseController{

    @RequestMapping("/changeMenuGroup")
    public ModelAndView changeMenu() {
        return new ModelAndView("manage/common/leftMenu");
    }

}
