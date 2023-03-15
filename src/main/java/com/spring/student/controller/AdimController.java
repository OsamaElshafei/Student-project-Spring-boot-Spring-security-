package com.spring.student.controller;

import com.spring.student.Service.StudentServices;
import com.spring.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ADMIN/")
public class AdimController {
    private StudentServices studentServices;
    @Autowired
    public AdimController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @PostMapping ("AddStudent")
    public Student AddStudent (@RequestBody Student student) {
        return studentServices.Addstudent(student);
    }
    @PutMapping("Update")
    public Student UpdateStudet (@RequestBody Student student,@RequestParam Long id) {
        student.setId(id);
        return studentServices.UpdateStudent(student);
    }
    @DeleteMapping("delete")
    public void UpdateStudet (@RequestParam Long id) {
         studentServices.deleteStudetbyID(id);
    }


}
