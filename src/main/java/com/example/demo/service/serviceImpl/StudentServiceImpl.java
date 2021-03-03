package com.example.demo.service.serviceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

   private StudentRepo studentRepo;
   private CourseRepo courseRepo;

   @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
       this.courseRepo = courseRepo;
   }

    @Override
    public void changePassword(String password, String email){
        Student student = studentRepo.findByEmail(email);
        student.setPassword(password);
        studentRepo.save(student);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    @Override
    public Student verifyStudentLogin(UserDTO userDTO) throws Exception {
        Optional<Student> student = studentRepo.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassWord());
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @Override
    public void registerCourse(Student student, Course courseToRegister){
        student.registerCourse(courseToRegister);
    }
    @Override
    public Student saveOrUpdate(Student student){
       return studentRepo.save(student);
    }

    @Override
    public List<Course> viewAllRegisteredCourses(Student student){
    return courseRepo.findByStudent(student);
    }

    @Override
    public void unRegisterStudentCourse(Student student, Course course){
        Set<Course> currentCourses = student.getCourses();
        currentCourses.remove(course);
        this.saveOrUpdate(student);
    }
}
