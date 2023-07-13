package me.rudnikov.backend.controller;

import me.rudnikov.backend.service.ExcelReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;


import java.io.IOException;

@RestController
@RequestMapping(
        value = "/api/v1/reports"
)
@AllArgsConstructor
public class ExcelReportController {

    private final ExcelReportService excelReportService;

    // Create report by professor id (HTTP::GET)
    @RequestMapping(
            value = "/professors/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<String> createProfessorReport(@PathVariable("id") Long id) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(excelReportService.createProfessorReport(id));
    }

}