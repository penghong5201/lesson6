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
import com.biz.lesson.model.user.MenuItem;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/menuItems")
@Secured("ROLE_MENUITEM")
public class MenuItemController  extends BaseController{

    @Autowired
    @Qualifier("userDetailsManager")
    private UserManager manager;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_ADD')")
    public ModelAndView add(@RequestParam("mainMenuId") Long id) {
        MenuItem menuItem = new MenuItem();
        menuItem.setMainMenu(manager.getMainMenu(id));
        ModelAndView view = new ModelAndView("manage/menuitem/add", "menuitem", menuItem);
        view.addObject("cmd", "add");
        return view;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_EDIT')")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/menuitem/add", "menuitem", manager.getMenuItem(id));
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_DELETE')")
    public ModelAndView delete(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("manage/menuitem/add", "menuitem", manager.getMenuItem(id));
        view.addObject("cmd", "delete");
        return view;
    }

    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_DETAIL')")
    public ModelAndView detail(@RequestParam("id") Long id) {
        return new ModelAndView("manage/menuitem/detail", "menuitem", manager.getMenuItem(id));
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_ADD')")
    public ModelAndView save_add(MenuItem menuItem) {
        manager.saveMenuItem(menuItem);
        long mainMenuId = menuItem.getMainMenu().getId();
        return new ModelAndView("redirect:/manage/mainMenus/detail.do?id=" + mainMenuId);
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_EDIT')")
    public ModelAndView save_edit(MenuItem menuItem) {
        // why copy ManyToMany
        MenuItem inDb = manager.getMenuItem(menuItem.getId());
        inDb.copy(menuItem);
        manager.updateMenuItem(inDb);
        long mainMenuId = menuItem.getMainMenu().getId();
        return new ModelAndView("redirect:/manage/mainMenus/detail.do?id=" + mainMenuId);
    }

    @RequestMapping("/save_delete")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_DELETE')")
    public ModelAndView save_delete(@RequestParam("id") Long id) {
        MenuItem menuItem = manager.getMenuItem(id);
        long mainMenuId = menuItem.getMainMenu().getId();
        manager.deleteMenuItem(menuItem);
        return new ModelAndView("redirect:/manage/mainMenus/detail.do?id=" + mainMenuId);
    }

    @RequestMapping("/changeMenu")
    @PreAuthorize("hasAuthority('OPT_MENUITEM_CHANGEMENU')")
    public ModelAndView changeMenu() {
        ModelAndView modelAndView = new ModelAndView("manage/menuitem/changeMenu", "mainmenus", manager.listAllMainMenu());
        return modelAndView;
    }

    @RequestMapping("/updateItemMenuParentMenu")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_CHANGPARENTMENU')")
    public ModelAndView updateItemMenuParentMenu(
            @RequestParam("menuItemIds") Long[] menuItemIds, @RequestParam("itemParentIds") String[] itemParentIds) {
        if (itemParentIds != null && itemParentIds.length > 0) {
            for (int i = 0; i < itemParentIds.length; i++) {
                if (!"".equals(itemParentIds[i])) {
                    manager.updateItemParentId(Long.parseLong(itemParentIds[i]), menuItemIds[i]);
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("manage/menuitem/changeMenu", "mainmenus", manager.listAllMainMenu());
        return modelAndView;
    }
}
