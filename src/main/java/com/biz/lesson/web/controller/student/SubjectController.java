package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.SubjectService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.vo.student.SubjectVo;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("subject/subject")
public class SubjectController extends BaseController{
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;


    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/list")
    //下面注解内为命名。模块简称
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_LIST')")//has是赋予操作权限
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/list");
        List<Subject> subjects = subjectService.list();
        modelAndView.addObject("subjects", subjects);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/add");
        modelAndView.addObject("cmd", "add");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_ADD')")
    // BindingResult result  做校验约束用，例如长度校验
    public ModelAndView save_add(SubjectVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        //将VO里面的东西保存到PO
        Subject subject = new Subject();
        copyProperties(vo, subject);
        subjectService.create(subject);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/add");
        Subject subject = subjectService.get(id);
        BusinessAsserts.exists(subject, id);

        modelAndView.addObject("subject", subject);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_EDIT')")
    public ModelAndView save_edit(SubjectVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Subject subject = subjectService.get(vo.getId());
        BusinessAsserts.exists(subject, vo.getId());

        copyProperties(vo, subject);

        subjectService.update(subject);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") String id) {
        Subject subject = subjectService.get(id);
        try {
            subjectService.delete(subject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
