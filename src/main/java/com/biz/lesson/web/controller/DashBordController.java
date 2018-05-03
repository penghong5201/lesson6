package com.biz.lesson.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class DashBordController  {

    @RequestMapping("welcome")
    public ModelAndView welcome(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        return modelAndView;
    }

	@RequestMapping("dashboard")
    public ModelAndView dashBord() {
    	ModelAndView modelAndView = new ModelAndView("dashboard");

    	return modelAndView;
    }

	
    
    
}
