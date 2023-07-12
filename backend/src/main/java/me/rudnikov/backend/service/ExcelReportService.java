package me.rudnikov.backend.service;

import java.io.IOException;

public interface ExcelReportService {

    String createProfessorReport(Long id) throws IOException;

}