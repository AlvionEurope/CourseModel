package ru.wxw.coursemodel.wxw.service;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wxw.coursemodel.wxw.entity.Repotr;
import ru.wxw.coursemodel.wxw.repository.ReportRepository;

import java.io.*;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    public byte[] getProfAndSumStudentAndStudentAverage() {
        List<Repotr> reportList = reportRepository.getProfAndSumStudentAndStudentAverage();
        try  {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Professor");
            Row header = sheet.createRow(0);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Professor");
            headerCell = header.createCell(1);
            headerCell.setCellValue("SumStudentAllCourse");
            headerCell = header.createCell(2);
            headerCell.setCellValue("AverageStage");

            for (int i = 0; i < reportList.size(); i++) {
                Repotr repotr = reportList.get(i);
                Row row = sheet.createRow(i + 1);

                Cell cell = row.createCell(0);
                cell.setCellValue(repotr.getNameProfessor());

                cell = row.createCell(1);
                cell.setCellValue(repotr.getSumStudentAllCourse());

                cell = row.createCell(2);
                cell.setCellValue(repotr.getAverageStageAllStudent());
            }
            sheet.autoSizeColumn(0);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
