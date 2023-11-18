package com.acciojob.library.management.system.controllers;

import com.acciojob.library.management.system.entitys.Student;
import com.acciojob.library.management.system.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServices studentServices;
    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student) {
        String result = studentServices.addStudent(student);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
