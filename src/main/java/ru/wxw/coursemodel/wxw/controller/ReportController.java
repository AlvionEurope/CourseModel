package ru.wxw.coursemodel.wxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wxw.coursemodel.wxw.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/get")
    public ResponseEntity<byte[]> get() {
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(reportService.getProfAndSumStudentAndStudentAverage());
    }
}