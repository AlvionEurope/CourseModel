package com.courseModel.controller;

import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor

public class StudentController {
    final StudentService service;

    @PostMapping
    public StudentDTO create(@Valid @RequestBody CreateStudentRequest request) {
        return service.create(request);
    }

    @GetMapping(value = "/{grade-book}")
    public StudentDTO readByGradeBook(@PathVariable(name = "grade-book") int gradeBook) {
        return service.readById(gradeBook);
    }

    @PutMapping(value = "/{grade-book}")
    public StudentDTO updateByGradeBook(@PathVariable(name = "grade-book") int gradeBook, @Valid @RequestBody CreateStudentRequest request) {
        return service.updateById(gradeBook, request);
    }

    @DeleteMapping(value = "/{grade-book}")
    public boolean deleteByGradeBook(@PathVariable(name = "grade-book") int gradeBook) {
        return service.deleteById(gradeBook);
    }
}
