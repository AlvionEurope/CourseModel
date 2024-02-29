package com.courseModel.controller;

import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.service.ProfessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


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
    @ApiOperation(value = "Редактирование профессора по id")
    public ProfessorDTO updateById(@PathVariable(name = "id") int id, @Valid @RequestBody CreateProfessorRequest request) {
        return service.updateById(id, request);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Удаление профессора по id")
    public boolean deleteById(@PathVariable(name = "id") int id) {
        return service.deleteById(id);
    }
    @GetMapping(value="/report")
    @ApiOperation(value = "Создание отчета для профессоров")
    public HttpEntity<ByteArrayResource> createExcelWithTaskConfigurations() throws IOException {
        byte[] excelContent = service.createReport();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=professorReport.xlsx");
        return new HttpEntity<>(new ByteArrayResource(excelContent), header);
    }
}
