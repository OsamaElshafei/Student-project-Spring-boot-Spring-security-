package com.spring.student.controller;

import com.spring.student.Service.StudentServices;
import com.spring.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("System/")
public class StudentController {
    private StudentServices studentServices;
    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }
    @GetMapping("Students")
    public List<Student> getStudents(@RequestParam int page,@RequestParam int size){
        return studentServices.getAllStudent(page, size);
    }

    @GetMapping("Student")
    public Student getstudentbyID (@RequestParam Long id) {
        return studentServices.getstudentbyID(id);
    }

    public List<Student> findstudentbyname (@RequestParam String name,@RequestParam int page,@RequestParam int size){
        return studentServices.findstudentbyname(name,page,size);
    }
    @GetMapping("student/length")
    public int findstudentbyname (@RequestParam String name){
        return studentServices.findstudentlengthbyname(name);
    }
    @GetMapping("students/size")
    public Long studentsize(){
        return studentServices.studentsize();
    }

}
