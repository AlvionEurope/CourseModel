package com.example.course.controller;

import com.example.course.model.Course;
import com.example.course.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/course")
    public Course postStudent(@RequestBody Course course) {
        return service.postCourse(course);
    }

    @GetMapping("/course/{id}")
    public Course getStudent(@PathVariable Long id) {
        return service.getCourse(id);
    }

    @DeleteMapping("/course/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteCourse(id);
    }

    @PutMapping("/course/{id}")
    public Course putStudent(@RequestBody Course course,
                             @PathVariable Long id) throws Exception {
        return service.putCourse(id, course);
    }

    //Добавить студента на курс
    @PostMapping("/course/{id}")
    public void addStudentOnCourse(@PathVariable Long id,
                                   @RequestParam(value = "studentId") Long studentId) {
        service.addStudentOnCourse(id, studentId);
    }

    //Удалить студента с курса
    @DeleteMapping("/course/{id}/delete")
    public void deleteStudentOnCourse(@PathVariable Long id,
                                      @RequestParam(value = "studentId") Long studentId) {
        service.deleteStudentCourse(id, studentId);
    }
}
