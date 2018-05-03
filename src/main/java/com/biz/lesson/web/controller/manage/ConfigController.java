package com.biz.lesson.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.Constants;
import com.biz.lesson.business.config.ConfigManager;
import com.biz.lesson.model.config.SystemProperty;
import com.biz.lesson.vo.user.SystemPropertyVo;
import com.biz.lesson.web.controller.BaseController;


@Controller
@RequestMapping("/manage/config")
@Secured("ROLE_CONFIG")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigManager configManager;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_CONFIG_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("/manage/config/list");
        modelAndView.addObject("systemProperties",configManager.listProperties());
        return modelAndView;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_CONFIG_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) throws Exception {
        try {
            configManager.deleteSystemProperty(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_CONFIG_ADD')")
    public ModelAndView add() throws Exception {
        ModelAndView modelAndView = new ModelAndView("/manage/config/add");
        modelAndView.addObject(Constants.CMD,Constants.ADD);
        return modelAndView;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_CONFIG_EDIT')")
    public ModelAndView edit(@RequestParam("id") String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/manage/config/add");
        modelAndView.addObject("property",configManager.getSystemProperty(id));
        modelAndView.addObject(Constants.CMD,Constants.EDIT);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_CONFIG_ADD')")
    public ModelAndView save_add(SystemPropertyVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        error(result, request);
        if (logger.isDebugEnabled()) {
            logger.debug(vo.toString());
        }

        SystemProperty po = new SystemProperty();
        copyProperties(vo, po);
        configManager.saveSystemProperty(po);

        String referer = request.getHeader(Constants.REFERER);
        if (StringUtils.isEmpty(referer)) {
            referer = "/welcome.do";
        }

        return new ModelAndView("redirect:"+referer);
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_CONFIG_EDIT')")
    public ModelAndView save_edit(SystemPropertyVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        error(result, request);
        if (logger.isDebugEnabled()) {
            logger.debug(vo.toString());
        }

        SystemProperty po = configManager.getSystemProperty(vo.getId());
        copyProperties(vo, po);
        configManager.saveSystemProperty(po);

        String referer = request.getHeader(Constants.REFERER);
        if (StringUtils.isEmpty(referer)) {
            referer = "/welcome.do";
        }

        return new ModelAndView("redirect:"+referer);
    }


}
