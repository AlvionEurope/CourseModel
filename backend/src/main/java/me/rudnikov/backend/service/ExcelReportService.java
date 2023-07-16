package me.rudnikov.backend.service;

import java.io.IOException;

public interface ExcelReportService {

    String createAllProfessorsReport() throws IOException;
    String createProfessorReport(Long id) throws IOException;
}