package com.acciojob.library.management.system.services;

import com.acciojob.library.management.system.entitys.Student;
import com.acciojob.library.management.system.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {
    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "student added successfully";
    }
}
