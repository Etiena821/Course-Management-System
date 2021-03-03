package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.service.AdminService;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CourseController {

    private CourseService courseService;
    private StudentService studentService;
    private AdminService adminService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService, AdminService adminService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("/course")
    public String viewHomePage(Model model, HttpSession session, UserDTO userDTO) throws Exception {
        Student student = new Student();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin != null) {
            model.addAttribute("courselist", courseService.getAllRegisteredCourses(student));
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewEmployeeForm(HttpSession session, Model model){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";

    }
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        courseService.saveRegisteredCourse(course);
        return "redirect:/course";

    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Course course = courseService.getRegisteredCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }


    @GetMapping("/viewCourseDetails/{id}")
    public String viewEmployeDetails(@PathVariable Long id,  Model model, HttpSession session, Student student) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Course course = courseService.getRegisteredCourseById(id);

        model.addAttribute("course", course);
        model.addAttribute("studentcourses", studentService.viewAllRegisteredCourses(student));
        return "view-course-details";
    }

}
