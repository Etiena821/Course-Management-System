package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student saveOrUpdate(Student student);
    List<Course> viewAllRegisteredCourses(Student student);
    void unRegisterStudentCourse(Student student, Course course);
    void registerCourse(Student student, Course courseToRegister);
    void changePassword(String password, String email);
    Student getStudentByEmail(String email);
    Student verifyStudentLogin(UserDTO userDTO) throws Exception;

}
