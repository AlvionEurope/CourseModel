package com.courseModel.service;

import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.dto.ReportDTO;
import com.courseModel.entity.Course;
import com.courseModel.entity.Professor;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.ProfessorMapper;
import com.courseModel.repository.CourseRepository;
import com.courseModel.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    final ProfessorRepository professorRepository;
    final CourseRepository courseRepository;
    final ProfessorMapper mapper;
    private final static String[] COLUMNS = {"Профессор", "Количество студентов", "Средняя успеваемость студентов"};

    @Override
    public ProfessorDTO create(CreateProfessorRequest request) {
        Professor professor = mapper.convert(request);
        return mapper.convert(professorRepository.save(professor));
    }

    @Override
    public ProfessorDTO readById(int id) {
        return mapper.convert(getProfessor(id));
    }

    @Override
    public ProfessorDTO updateById(int id, CreateProfessorRequest request) {
        Professor professor = getProfessor(id);
        professor.setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setPayment(request.getPayment());
        return mapper.convert(professorRepository.save(professor));
    }

    @Transactional
    @Override
    public boolean deleteById(int id) {
        if (professorRepository.existsById(id)) {
            List<Course> courses = courseRepository.findByProfessorId(id);
            for (Course course : courses) {
                course.setProfessorId(null);
            }
            courseRepository.saveAll(courses);
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private List<ReportDTO> getReportList() {
        return professorRepository.getReportProfessor()
                .stream()
                .map(row -> new ReportDTO()
                        .setNameProfessor(row.get("name"))
                        .setQuantityStudent(objectToString(row.get("count_students")))
                        .setAvgGrade(objectToString(row.get("avr_score"))))
                .collect(Collectors.toList());
    }

    private byte[] listReportDTOToXlsx(List<ReportDTO> reportDTOList) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Workbook wb = new XSSFWorkbook();
            addReportDTOListToBook(wb, reportDTOList);
            wb.write(bos);
            return bos.toByteArray();
        } catch ( IOException e ) {
            throw new RuntimeException("Ошибка во время создания xslx файла", e);
        }
    }

    public byte[] createReport() {
        List<ReportDTO> reportList = getReportList();
        return listReportDTOToXlsx(reportList);
    }

    private String objectToString(Object object) {
        return object == null ? null : object.toString();
    }

    public Professor getProfessor(int id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Профессор с id %d не найден", id)));
    }

    private void addReportDTOListToBook(Workbook wb, List<ReportDTO> reportDTOList) {
        Sheet sheet = wb.createSheet("Отчет");
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < COLUMNS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(COLUMNS[i]);
            cell.setCellStyle(headerCellStyle);
        }
        int rowNum = 1;
        for (ReportDTO report : reportDTOList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(report.getNameProfessor());
            row.createCell(1).setCellValue(report.getQuantityStudent());
            row.createCell(2).setCellValue(report.getAvgGrade());
        }
        for (int i = 0; i < COLUMNS.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
