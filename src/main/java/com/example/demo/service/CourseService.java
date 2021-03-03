package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> getAllRegisteredCourses(Student student);
    void saveRegisteredCourse(Course course);
    Course getRegisteredCourseById(long id);
    void deleteCourseById(long id);
}
