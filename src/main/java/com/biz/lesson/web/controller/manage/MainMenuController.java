package com.biz.lesson.web.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.MainMenu;
import com.biz.lesson.web.controller.BaseController;

@Controller
@RequestMapping("/manage/mainMenus")
@Secured("ROLE_MAINMENU")
public class MainMenuController extends BaseController{

    @Autowired
    @Qualifier("userDetailsManager")
    private UserManager manager;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_MAINMENU_LIST')")
    public ModelAndView list()throws Exception  {
        return new ModelAndView("manage/mainmenu/list", "mainmenus", manager.listAllMainMenu());
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_ADD')")
    public ModelAndView add() throws Exception {
        ModelAndView view = new ModelAndView("manage/mainmenu/add");
        view.addObject("cmd", "add");
        return view;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_EDIT')")
    public ModelAndView edit(@RequestParam("id") Long id) throws Exception {
        ModelAndView view = new ModelAndView("manage/mainmenu/add", "mainmenu", manager.getMainMenu(id));
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_DELETE')")
    public ModelAndView delete(@RequestParam("id") Long id) throws Exception {
        ModelAndView view = new ModelAndView("manage/mainmenu/add", "mainmenu", manager.getMainMenu(id));
        view.addObject("cmd", "delete");
        return view;
    }

    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_DETAIL')")
    public ModelAndView detail(@RequestParam("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("manage/mainmenu/detail", "mainmenu", manager.getMainMenu(id));
        return modelAndView;
    }

    /**
     * 不推荐这种写法
     * 最好把入参MainMenu 改为 Vo
     */
    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_ADD')")
    public ModelAndView save_add(MainMenu menu) throws Exception {
        MainMenu po = manager.saveMainMenu(menu);
        return new ModelAndView("redirect:/manage/mainMenus/detail.do?id="+po.getId());
    }

    /**
     * 不推荐这种写法
     * 最好把入参MainMenu 改为 Vo
     */
    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_EDIT')")
    public ModelAndView save_edit(MainMenu menu) throws Exception {
        MainMenu existMainMenu = manager.getMainMenu(menu.getId());
        existMainMenu.setName(menu.getName());
        existMainMenu.setNameEn(menu.getNameEn());
        existMainMenu.setCode(menu.getCode());
        existMainMenu.setIcon(menu.getIcon());
        existMainMenu.setDescription(menu.getDescription());
        manager.updateMainMenu(existMainMenu);
        return new ModelAndView("redirect:/manage/mainMenus.do");
    }

    
    @RequestMapping("/save_delete")
    @PreAuthorize("hasAuthority('OPT_MAINMENU_DELETE')")
    public ModelAndView save_delete(@RequestParam("id") Long id) throws Exception {
        MainMenu menu = manager.getMainMenu(id);
        manager.deleteMainMenu(menu);
        return new ModelAndView("redirect:/manage/mainMenus.do");
    }

}
