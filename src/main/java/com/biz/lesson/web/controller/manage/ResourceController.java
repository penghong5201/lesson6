package com.biz.lesson.web.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.Resource;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/resources")
@Secured("ROLE_RESOURCE")
public class ResourceController  extends BaseController{

    @Autowired
    @Qualifier("userDetailsManager")
    private UserManager manager;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_ADD')")
    public ModelAndView add(@RequestParam("menuItemId") Long id) {
        Resource resource = new Resource();
        resource.setMenuItem(manager.getMenuItem(id));
        ModelAndView view = new ModelAndView("manage/resource/add", "resource", resource);
        view.addObject("cmd", "add");
        return view;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_EDIT')")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/resource/add", "resource", manager.getResource(id));
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_DELETE')")
    public ModelAndView delete(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/resource/add", "resource", manager.getResource(id));
        view.addObject("cmd", "delete");
        return view;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_ADD')")
    public ModelAndView save_add(Resource resource) {
        manager.createResource(resource);
        long menuItemId = resource.getMenuItem().getId();
        return new ModelAndView("redirect:/manage/menuItems/detail.do?id=" + menuItemId);
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_EDIT')")
    public ModelAndView save_edit(Resource resource) {
        // why copy ManyToMany
        Resource inDb = manager.getResource(resource.getId());
        inDb.copy(resource);
        manager.updateResource(inDb);
        long menuItemId = resource.getMenuItem().getId();
        return new ModelAndView("redirect:/manage/menuItems/detail.do?id=" + menuItemId);
    }

    @RequestMapping("/save_delete")
    @PreAuthorize("hasAuthority('OPT_RESOURCE_DELETE')")
    public ModelAndView save_delete(@RequestParam("id") Long id) {
        Resource resource = manager.getResource(id);
        long menuItemId = resource.getMenuItem().getId();
        manager.deleteResource(resource);
        return new ModelAndView("redirect:/manage/menuItems/detail.do?id=" + menuItemId);
    }
}
