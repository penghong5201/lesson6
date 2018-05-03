package com.biz.lesson.web.controller.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.Role;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/roles")
@Secured("ROLE_ROLE")
public class RoleController  extends BaseController{

    @Autowired
    @Qualifier("userDetailsManager")
    private UserManager manager;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_ROLE_LIST')")
    public ModelAndView list() {
        return new ModelAndView("manage/role/list", "roles", manager.listAllRoles());
    }

    @RequestMapping("add")
    @PreAuthorize("hasAuthority('OPT_ROLE_ADD')")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("manage/role/add");
        view.addObject("cmd", "add");
        view.addObject("mainmenus", manager.listAllMainMenu());
        return view;
    }

    @RequestMapping("edit")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/role/add", "role", manager.getRole(id));
        view.addObject("cmd", "edit");
        view.addObject("mainmenus", manager.listAllMainMenu());
        return view;
    }

    @RequestMapping("delete")
    @PreAuthorize("hasAuthority('OPT_ROLE_DELETE')")
    public ModelAndView delete(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/role/add", "role", manager.getRole(id));
        view.addObject("cmd", "delete");
        view.addObject("mainmenus", manager.listAllMainMenu());
        return view;
    }

    @RequestMapping("detail")
    @PreAuthorize("hasAuthority('OPT_ROLE_DETAIL')")
    public ModelAndView detail(@RequestParam("id") Long id) {
        return new ModelAndView("manage/role/detail", "role", manager.getRole(id));
    }

    @RequestMapping("save_add")
    @PreAuthorize("hasAuthority('OPT_ROLE_ADD')")
    public ModelAndView save_add(Role role) {
        manager.createRole(role);
        return new ModelAndView("redirect:/manage/roles.do");
    }

    @RequestMapping("save_edit")
    @PreAuthorize("hasAuthority('OPT_ROLE_EDIT')")
    public ModelAndView save_edit(Role role) {
        manager.updateRole(role);
        return new ModelAndView("redirect:/manage/roles.do");
    }

    @RequestMapping("save_delete")
    @PreAuthorize("hasAuthority('OPT_ROLE_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") Long id) {
        try {
            Role role = manager.getRole(id);
            // menu.getMenuItems().clear();
            manager.deleteRole(role);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    // 用来维护多对多信息
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "menuItems", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Role) {
                    return element;
                }
                if (element instanceof String) {
                    return manager.getMenuItem(Long.valueOf(element.toString()));
                }
                return null;
            }
        });
        binder.registerCustomEditor(List.class, "resources", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Role) {
                    return element;
                }
                if (element instanceof String) {
                    return manager.getResource(Long.valueOf(element.toString()));
                }
                return null;
            }
        });
    }
}
