package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    @GetMapping("/student-courses")
    public String viewHomePage(Model model, HttpSession session, UserDTO userDTO) throws Exception {
        Student student = new Student();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin != null) {
            model.addAttribute("courselist", courseService.getAllRegisteredCourses(student));
            return "student_dashboard";
        }
        return "redirect:/";
    }

    @PostMapping("{id}/student-course")
    public String registerCourses(Model model, @PathVariable(value = "id") Long id) {
//        Student student = new Student();
//        student.setCourses(courseService.getRegisteredCourseById(id));
//        model.addAttribute("student", student);
        return "";
    }

    @PostMapping("/")
    public String changePassword(Model model, HttpSession session, Student student){
//        String password = student.getPassword();
//        studentService.changePassword(password, session.getAttribute("student"));
        return "redirect:/homepage";
    }
}
