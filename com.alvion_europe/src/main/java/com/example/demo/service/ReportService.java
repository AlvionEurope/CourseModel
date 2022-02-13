package com.example.demo.service;

import com.example.demo.entity.CourseCompletion;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {
    void getReport(HttpServletResponse response);
}
