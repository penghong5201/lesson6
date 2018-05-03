package com.biz.lesson.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.model.user.User;
import com.biz.lesson.util.PageControl;
import com.biz.lesson.web.filter.BindThreadLocalFilter;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;
import com.biz.lesson.web.servlet.MessageSourceUtil;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageControl.class);

    @RequestMapping("{path}")
    public ModelAndView forwardToJsp(@PathVariable String path) {
        logger.debug("Received /{} request.", path);
        return new ModelAndView(path);
    }

    @RequestMapping("welcome1")
    public ModelAndView forwardToWelcome() {
        logger.debug("Received /welcome request.");

        User user = DataStorageThreadLocalHolder.getUser();
        BindThreadLocalFilter.cleanActiveMenu();
        String message = MessageSourceUtil.getI18nMsg("common.name");
        return new ModelAndView("welcome").addObject("msg", message);
    }

}
