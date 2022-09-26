package ru.alvion.coursemodel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.Professor;
import ru.alvion.coursemodel.service.dto.ProfessorDTO;
import ru.alvion.coursemodel.service.mapper.ProfessorMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class XlsxService {
    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    private final ProfessorService professorService;
    private final ProfessorMapper professorMapper;

    public XlsxService(ProfessorService professorService, ProfessorMapper professorMapper) {
        this.professorService = professorService;
        this.professorMapper = professorMapper;
    }

    public byte[] generateProfessorReport() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Профессоры");
            Row header = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Профессор");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("Студентов");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(2);
            headerCell.setCellValue("Средний балл");
            headerCell.setCellStyle(headerStyle);

            List<ProfessorDTO> professorDTOS = professorService.findAll();

            for (int i = 0; i < professorDTOS.size(); i++) {
                Professor professor = professorMapper.toEntity(professorDTOS.get(i));
                Row row = sheet.createRow(i + 1);

                Cell cell = row.createCell(0);
                cell.setCellValue(professor.getFullName());

                cell = row.createCell(1);
                cell.setCellValue(professorService.countStudents(professor));

                cell = row.createCell(2);
                cell.setCellValue(professorService.countAvgGrades(professor));
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
