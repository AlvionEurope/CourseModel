package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.Services;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    private Services services;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        services.addStudents(student);
        return "студент с именем " + student.getName() + " добавлен!";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        services.removeStudents(id);
        return "студент с " + id + "удален!";
    }

    @GetMapping("/course/{id}")
    public List<Course> getCourses(@PathVariable int id) {
        return services.getCourse(id);
    }
}
