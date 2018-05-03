package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.vo.student.GradeVo;
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
@RequestMapping("grade/grade")
public class GradeController extends BaseController{
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;


    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/list")
    //下面注解内为命名。模块简称
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_LIST')")//has是赋予操作权限
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/list");
        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades", grades);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/add");
        modelAndView.addObject("cmd", "add");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_ADD')")
    // BindingResult result  做校验约束用，例如长度校验
    public ModelAndView save_add(GradeVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        //将VO里面的东西保存到PO
        Grade grade = new Grade();
        copyProperties(vo, grade);
        gradeService.create(grade);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/add");
        Grade grade = gradeService.get(id);
        BusinessAsserts.exists(grade, id);

        modelAndView.addObject("grade", grade);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_EDIT')")
    public ModelAndView save_edit(GradeVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Grade grade = gradeService.get(vo.getId());
        BusinessAsserts.exists(grade, vo.getId());

        copyProperties(vo, grade);

        gradeService.update(grade);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") String id) {
        Grade grade = gradeService.get(id);
        try {
            gradeService.delete(grade);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
