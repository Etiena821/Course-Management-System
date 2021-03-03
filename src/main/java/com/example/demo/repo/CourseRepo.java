package com.example.demo.repo;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByStudent(Student student);
}
