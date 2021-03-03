package com.example.demo.service.serviceImpl;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repo.CourseRepo;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseImpl implements CourseService {

    private CourseRepo courseRepo;

    @Autowired
    public CourseImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> getAllRegisteredCourses(Student student) {
        return courseRepo.findAll() ;
    }

    @Override
    public void saveRegisteredCourse(Course course) {
        this.courseRepo.save(course);
    }

    @Override
    public Course getRegisteredCourseById(long id) {
        return courseRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("No such course found"));
    }

    @Override
    public void deleteCourseById(long id) {
        this.deleteCourseById(id);
    }

}
