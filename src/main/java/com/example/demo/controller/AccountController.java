package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Student;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private final AdminService adminService;
    private StudentService studentService;

    @Autowired
    public AccountController(AdminService adminService, StudentService studentService) {
        this.adminService = adminService;
        this.studentService = studentService;
    }

    // Login form
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("admin", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("admin") UserDTO userDTO, HttpSession session) throws Exception {
        if (userDTO.getEmail().contains("@admin.com")){
            Admin admin = adminService.verifyLogin(userDTO);
            if (admin != null){
                session.setAttribute("admin", admin);
                return "redirect:/course";
            }
            return "redirect:/";
        }
        return "redirect:/";
    }

//    // Login form
//    @GetMapping("/student")
//    public String studentLogin(Model model) {
//        model.addAttribute("student", new UserDTO());
//        return "login";
//    }

    @PostMapping("/login")
    public String studentLogin(@ModelAttribute("student") UserDTO userDTO, HttpSession session) throws Exception {
        if (userDTO.getEmail().contains("@student.com")) {
            Student student = studentService.verifyStudentLogin(userDTO);
            if(student != null) {
                session.setAttribute("student", student);
                return "redirect:/student-dashboard";
            }
            return "redirect:/";
        }
        return "redirect:/";
    }



}
