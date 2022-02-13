package com.example.demo.service;

import com.example.demo.entity.CourseCompletion;
import com.example.demo.repository.CourseCompetitionRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private CourseCompetitionRepository repository;
    @Override
    public void getReport(HttpServletResponse response) {
         repository.getReport(response);
    }
}
