package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.business.student.StuSubService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.business.student.SubjectService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.StuSub;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.vo.student.SearchVo;
import com.biz.lesson.vo.student.StuSubVo;
import com.biz.lesson.vo.student.StudentVo;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("student/student")
public class StudentController  extends BaseController{
    @Autowired
    @Qualifier("studentService")
    private StudentService studentservice;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired(required = false)
    private StuSubService stuSubService;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = studentservice.list();
        //Map map = new HashMap();
        for(Student student:students){
            //map.put(student.getId(),stuSubService.find(student.getId()).size());
            student.setCount(stuSubService.find(student.getId()).size());
        }
       //modelAndView.addObject("map",map);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        modelAndView.addObject("cmd", "add");
        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades",grades);
        addReferer(request);
        return modelAndView;
    }
    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
        // BindingResult result  做校验约束用
    public ModelAndView save_add(StudentVo vo,String gradeId,  BindingResult result, HttpServletRequest request) throws Exception {
      //将VO里面的东西保存到PO
        Student student = new Student();
        Grade grade = gradeService.get(gradeId);
        student.setGrade(grade);
        student.setAvgscore(0);
        copyProperties(vo, student);
        studentservice.create(student);
        return referer(request);
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        Student student = studentservice.get(id);
        BusinessAsserts.exists(student, id);
        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades",grades);
        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }
    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView save_edit(StudentVo vo,String gradeId,  BindingResult result, HttpServletRequest request) throws Exception {
        Student student = studentservice.get(vo.getId());
        BusinessAsserts.exists(student, vo.getId());
        Grade grade = gradeService.get(gradeId);
        student.setGrade(grade);
        copyProperties(vo, student);

        studentservice.update(student);
        return referer(request);
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") String id) {
        Student student = studentservice.get(id);
        try {
            studentservice.delete(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView select(String id,HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/addSubject");
        modelAndView.addObject("cmd", "select");
        List<Subject> subjects = subjectService.list();
        modelAndView.addObject("subjects",subjects);
        Student student = studentservice.get(id);
        modelAndView.addObject("student",student);
        List<StuSub> stuSubs = stuSubService.find(id);
        List<Subject> newSubject = new ArrayList<Subject>();
        for(int i = 0;i<stuSubs.size();i++){
            newSubject.add(stuSubs.get(i).getSubject());
        }
        /*System.out.println(newSubject.get(0));*/
        modelAndView.addObject("newSubject",newSubject);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView save_select(StudentVo vo,BindingResult result, HttpServletRequest request) throws Exception {
        String[] subjectId = request.getParameterValues("subjectId");
        Student student = studentservice.get(vo.getId());
        List<StuSub> stuSubs = stuSubService.find(vo.getId());
        //如果选择栏为空，全部删除
        if(subjectId==null){
            for (int i=0;i<stuSubs.size();i++){
                stuSubService.delete(stuSubs.get(i));
            }
        }else {     //不为空在对其判断做增删操作
            for(int i=0;i<subjectId.length;i++){
                //用学生id和当前选择的课程id在选课表中进行查询，如果为空，代表未选择这门课
                if(stuSubService.findBytwo(vo.getId(),subjectId[i]).size()==0){
                    StuSub stuSub = new StuSub();
                    stuSub.setStudent(student);
                    Subject subject = subjectService.get(subjectId[i]);
                    stuSub.setSubject(subject);
                    stuSubService.create(stuSub);
                    System.out.println("111");
                }
            }
            //得到最新的选课表
            List<StuSub> stuSubs1 = stuSubService.find(vo.getId());
            //当前选择的课程
            List<StuSub> stuSubs2 = new ArrayList<>();
            for(int i=0;i<subjectId.length;i++){
                stuSubs2.add(stuSubService.findBytwo(vo.getId(),subjectId[i]).get(0));
            }
            //得到退选的课程
           stuSubs1.removeAll(stuSubs2);
            for(StuSub stuSub:stuSubs1){
                stuSubService.delete(stuSub);
                System.out.println("222");
            }
        }
        return referer(request);
    }

    @RequestMapping("/addscore")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADDSCORE')")
    public ModelAndView addscore(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/addScore");
        Student student = studentservice.get(id);
        List<StuSub> stuSubs = stuSubService.find(id);
        /*List<Subject> subjects = new ArrayList<Subject>();
        for(int i = 0;i<stuSubs.size();i++){
            subjects.add(stuSubs.get(i).getSubject());
        }*/
        modelAndView.addObject("stuSubs",stuSubs);
        modelAndView.addObject("student",student);
        modelAndView.addObject("cmd", "addscore");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_addscore")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADDSCORE')")
    public ModelAndView save_addscore(StuSubVo vo, BindingResult result, HttpServletRequest request) throws Exception {
            /*Student student = studentservice.get(vo.getStudent().getId());
            String[] subjectid = vo.getSubject().getId().split(",");
            Subject subject = subjectService.get(subjectid[0]);*/
            List<StuSub> stuSubs = stuSubService.find(vo.getStudent().getId());
            String[] scores = vo.getScore().split(",");
            /*stuSubs.get(0).setStudent(student);
            stuSubs.get(0).setSubject(subject);*/
            for(int i = 0;i<stuSubs.size();i++){
                stuSubs.get(i).setScore(Integer.parseInt(scores[i]));
                stuSubService.update(stuSubs.get(i));
            }
            return referer(request);
    }

    @RequestMapping("/search")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
    public ModelAndView search(SearchVo vo,BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = studentservice.search(vo.getStudentid(),vo.getName(),vo.getStime(),vo.getEtime());
        //Map map = new HashMap();
        for(Student student:students){
            //map.put(student.getId(),stuSubService.find(student.getId()).size());
            student.setCount(stuSubService.find(student.getId()).size());
        }
        //modelAndView.addObject("map",map);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
