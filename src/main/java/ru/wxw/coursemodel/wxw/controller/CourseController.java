package ru.wxw.coursemodel.wxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wxw.coursemodel.wxw.dto.StudentToCourseDTO;
import ru.wxw.coursemodel.wxw.service.CourseService;

@RestController
@RequestMapping("/course")

public class CourseController {
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<StudentToCourseDTO> addStudentToCourse(@RequestParam(value = "bookGrade") int bookGrade,
                                                                 @RequestParam(value = "numberCourse") int courseNumber) {
        StudentToCourseDTO studentToCourseDTO = courseService.addStudent(bookGrade, courseNumber);
        return studentToCourseDTO != null
                ? new ResponseEntity<>(studentToCourseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteStudentFromCourse(@RequestParam(value = "bookGrade") int bookGrade,
                                                              @RequestParam(value = "numberCourse") int courseNumber) {
        courseService.deleteStudent(bookGrade, courseNumber);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
