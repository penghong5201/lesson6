package com.biz.lesson.web.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.exception.BusinessException;
import com.biz.lesson.model.user.User;
import com.biz.lesson.vo.user.PasswordVo;
import com.biz.lesson.web.controller.BaseController;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;


@Controller
@RequestMapping("/password")
public class UserPasswordController extends BaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest servletRequest) throws Exception {
        ModelAndView modelAndView = new ModelAndView("password");

        User user = userManager.getUser(DataStorageThreadLocalHolder.getUser().getId());
        modelAndView.addObject("userId", user.getUserId());
        modelAndView.addObject("passwordRegexp", PasswordVo.PASSWORD_REGEXP);
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(PasswordVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        error(result, request);
        if (logger.isDebugEnabled()) {
            logger.debug(vo.toString());
        }

        User user = userManager.getUser(DataStorageThreadLocalHolder.getUser().getId());

        String oldPassword = vo.getPasswordOld();
        oldPassword = passwordEncoder.encodePassword(oldPassword, user.getUsername());
        if (!user.getPassword().equals(oldPassword)) {
            // 老密码错误
            throw new BusinessException("old password is incorrect");
        } else if (!vo.getPasswordNew().equals(vo.getPasswordRepeat())) {
            throw new BusinessException("old password is not equal repeat password");
        }

        user.setPassword(passwordEncoder.encodePassword(vo.getPasswordNew(), user.getUsername()));
        userManager.updateUser(user);

        return new ModelAndView("redirect:/welcome.do");
    }

}
