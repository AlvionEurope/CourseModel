package com.example.course.controller;

import com.example.course.model.Professor;
import com.example.course.service.ProfessorService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ProfessorController {

    final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping("/professor")
    public Professor postStudent(@RequestBody Professor professor) {
        return service.postProfessor(professor);
    }

    @GetMapping("/professor/{id}")
    public Professor getStudent(@PathVariable Long id) {
        return service.getProfessor(id);
    }

    @DeleteMapping("/professor/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteProfessor(id);
    }

    @PutMapping("/professor/{id}")
    public Professor putStudent(@RequestBody Professor professor,
                                @PathVariable Long id) throws Exception {
        return service.putProfessor(id, professor);
    }

    //Добавить профессора на курс
    @PostMapping("/professor/{id}/course")
    public void addProfessorCourse(@PathVariable Long id,
                                   @RequestParam(value = "courseId") Long courseId) {
        service.addProfessorCourse(id, courseId);
    }

    //Сформировать отчет
    @GetMapping("/professor/report")
    public ResponseEntity<InputStreamResource> reportDownload() throws IOException {
        String fileName = "professor_report.xlsx";
        ByteArrayInputStream inputStream = service.getDataDownloaded();
        InputStreamResource response = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
    }

}
