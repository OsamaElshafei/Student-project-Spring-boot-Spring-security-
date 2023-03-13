package com.spring.student.Service;

import com.spring.student.dao.StudentRepo;
import com.spring.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class StudentServices {
    private StudentRepo studentRepo;
    @Autowired
    public StudentServices(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    public List<Student> getAllStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepo.findAll(pageable).getContent();
    }
    public Student getstudentbyID (Long id){
        return studentRepo.findById(id).get();
    }
    public Student Addstudent (Student student){

        return studentRepo.save(student);
    }
    public Student UpdateStudent (Student student){
        return studentRepo.save(student);
    }

    public void deleteStudetbyID (@RequestParam Long id) {
        studentRepo.deleteById(id);
    }
    public List<Student> findstudentbyname (String name,int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return studentRepo.findByFullNameContains(name,pageable);
    }
    public int findstudentlengthbyname (String name){
        return studentRepo.findByFullNameContains(name).size();
    }
    public Long studentsize(){
        return studentRepo.studentsize();
    }




}
