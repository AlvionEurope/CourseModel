package example.service;

import example.dto.TeacherStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class XlsTeacherReportService implements TeacherReportService {
    private final TeacherService teacherService;

    @Override
    public byte[] generateWorkLoadReport() {
        List<TeacherStatistics> workload = teacherService.getTeachersWorkload();

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Persons");

            writeHeader(sheet, List.of("Teacher", "Student count", "Students average grade"));
            writeWorkloadRows(workload, sheet);

            return writeWorkbook(workbook);
        } catch (IOException e) {
            log.error("Error while writing excel to file");
            e.printStackTrace();
            return null;
        }
    }

    private byte[] writeWorkbook(XSSFWorkbook workbook) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream.toByteArray();
    }

    private void writeWorkloadRows(List<TeacherStatistics> workload, Sheet sheet) {
        for (int i = 0; i < workload.size(); i++) {
            TeacherStatistics statistics = workload.get(i);
            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(statistics.getTeacherName());

            cell = row.createCell(1);
            cell.setCellValue(statistics.getStudentsCount());

            cell = row.createCell(2);
            Float averageGrade = statistics.getAverageGrade();
            if (averageGrade != null) {
                cell.setCellValue(averageGrade);
            }
        }

        sheet.autoSizeColumn(0);
    }

    private void writeHeader(Sheet sheet, List<String> columnNames) {
        Row header = sheet.createRow(0);

        for (int i = 0; i < columnNames.size(); i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(columnNames.get(i));
            sheet.autoSizeColumn(i);
        }
    }
}
