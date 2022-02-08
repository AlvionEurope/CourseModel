package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MyController {

    @Autowired
    private CourseCompetitionService services;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    CourseService courseService;

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        studentService.create(student);
        return student;
    }

    @PostMapping("/courses")
    public Course addcourse(@RequestBody Course course) {
        courseService.create(course);
        return course;
    }


}
