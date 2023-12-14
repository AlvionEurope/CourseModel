package com.courseModel.controller;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.service.CourseService;
import com.courseModel.service.ProfessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/professor")
@RequiredArgsConstructor
@Api(tags = "professor")
public class ProfessorController {
    final ProfessorService service;

    @PostMapping
    @ApiOperation(value = "Создание профессора")
    public ProfessorDTO create(@Valid @RequestBody CreateProfessorRequest request) {
        return service.create(request);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Получение профессора по id")
    public ProfessorDTO readById(@PathVariable(name = "id") int id) {
        return service.readById(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Изменение курса по номеру курса")
    public ProfessorDTO updateById(@PathVariable(name = "id") int id, @Valid @RequestBody CreateProfessorRequest request) {
        return service.updateById(id, request);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Удаление профессора по id")
    public boolean deleteById(@PathVariable(name = "id") int id) {
        return service.deleteById(id);
    }
}
