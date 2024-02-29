package com.courseModel.controller;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/course")
@RequiredArgsConstructor
@Api(tags = "course")
public class CourseController {
    final CourseService service;

    @PostMapping
    @ApiOperation(value = "Создание курса")
    public CourseDTO create(@Valid @RequestBody CreateCourseRequest request) {
        return service.create(request);
    }

    @GetMapping(value = "/{course-number}")
    @ApiOperation(value = "Получение курса по номеру курса")
    public CourseDTO readByCourseNumber(@PathVariable(name = "course-number") int courseNumber) {
        return service.readByCourseNumber(courseNumber);
    }

    @PutMapping(value = "/{course-number}")
    @ApiOperation(value = "Изменение курса по номеру курса")
    public CourseDTO updateByCourseNumber(@PathVariable(name = "course-number") int courseNumber, @Valid @RequestBody CreateCourseRequest request) {
        return service.updateByCourseNumber(courseNumber, request);
    }

    @DeleteMapping(value = "/{course-number}")
    @ApiOperation(value = "Удаление курса по номеру курса")
    public boolean deleteByCourseNumber(@PathVariable(name = "course-number") int courseNumber) {
        return service.deleteByCourseNumber(courseNumber);
    }

    @PostMapping(value = "/{course-number}/student/{grade-book}")
    @ApiOperation(value = "Назначение студента на курс", notes = "создание обучения со статусом NOT_STARTED")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addStudent(@PathVariable(name = "course-number") int courseNumber, @PathVariable(name = "grade-book") int gradeBook) {
        service.addStudent(courseNumber, gradeBook);
    }

    @DeleteMapping(value = "/{course-number}/student/{grade-book}")
    @ApiOperation(value = "Удаление студента с курса", notes = "удаление студента из списка обучающихся")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable(name = "course-number") int courseNumber, @PathVariable(name = "grade-book") int gradeBook) {
        service.deleteStudent(courseNumber, gradeBook);
    }
}
