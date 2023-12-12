package com.courseModel.controller;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.service.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/course")
@RequiredArgsConstructor
public class CourseController {
    final CourseService service;

    @PostMapping
    public CourseDTO create(@Valid @RequestBody CreateCourseRequest request) {
        return service.create(request);
    }

    @GetMapping(value = "/{course-number}")
    public CourseDTO readByCourseNumber(@PathVariable(name = "course-number") int courseNumber) {
        return service.readById(courseNumber);
    }

    @PutMapping(value = "/{course-number}")
    public CourseDTO updateByCourseNumber(@PathVariable(name = "course-number") int courseNumber, @Valid @RequestBody CreateCourseRequest request) {
        return service.updateById(courseNumber, request);
    }

    @DeleteMapping(value = "/{course-number}")
    public boolean deleteByCourseNumber(@PathVariable(name = "course-number") int courseNumber) {
        return service.deleteById(courseNumber);
    }

    @PostMapping(value = "/{course-number}/student/{grade-book}")
    @ApiOperation(value = "Назначение студента на курс", notes = "создание обучения со статусом NOT_STARTED")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addStudent(@PathVariable(name = "course-number") int courseNumber, @PathVariable(name = "grade-book") int gradeBook) {
        service.addStudent(courseNumber, gradeBook);
    }

}
