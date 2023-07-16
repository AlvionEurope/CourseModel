package me.rudnikov.backend.service.impl;

import com.github.ckpoint.toexcel.core.ToWorkBook;
import com.github.ckpoint.toexcel.core.ToWorkSheet;
import com.github.ckpoint.toexcel.core.type.ToWorkBookType;
import lombok.AllArgsConstructor;
import me.rudnikov.backend.entity.*;
import me.rudnikov.backend.service.ExcelReportService;
import me.rudnikov.backend.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExcelReportServiceImpl implements ExcelReportService {

    private final ProfessorService professorService;
    private final ModelMapper modelMapper;

    private final String FILE_PATH = "backend/src/main/resources/excel/result.xlsx";

    public String createAllProfessorsReport() throws IOException {
        List<Professor> professors = professorService.readAllProfessors()
                .stream()
                .map(professorDto -> modelMapper.map(professorDto, Professor.class))
                .toList();

        ToWorkBook workBook = new ToWorkBook(ToWorkBookType.XSSF);
        ToWorkSheet sheet = workBook.createSheet();

        for (Professor professor : professors) {
            sheet.createTitleCell(3, "ФИО_Преподавателя", "Курс", "Количество_Студентов");
            sheet.newLine();

            for (Course course : professor.getCourses()) {
                sheet.createCell(course.getProfessor().getFullName(), course.getName(), course.getStudents().size());
                sheet.newLine();
            }

            sheet.newLine();

            sheet.createTitleCell(3, "Студент", "Курс", "Средняя_оценка");
            sheet.newLine();

            for (Course course : professor.getCourses()) {
                course
                        .getStudents()
                        .forEach(student -> {
                            sheet.createCell(
                                    student.getFullName(),
                                    course.getName(),
                                    student.getAveragePerformanceByCourseName(course.getName())
                            );
                            sheet.newLine();
                        });
            }
            sheet.newLine();
        }

        workBook.write(FILE_PATH);

        return FILE_PATH;
    }

    public String createProfessorReport(Long id) throws IOException {
        Professor professor = modelMapper
                .map(professorService.readProfessorById(id), Professor.class);

        List<Course> courses = professor.getCourses();

        ToWorkBook workBook = new ToWorkBook(ToWorkBookType.XSSF);
        ToWorkSheet sheet = workBook.createSheet();

        sheet.createTitleCell(3, "ФИО_Преподавателя", "Курс", "Количество_Студентов");
        sheet.newLine();

        for (Course course : courses) {
            sheet.createCell(course.getProfessor().getFullName(), course.getName(), course.getStudents().size());
            sheet.newLine();
        }

        sheet.newLine();

        sheet.createTitleCell(3, "Студент", "Курс", "Средняя_оценка");
        sheet.newLine();

        for (Course course : courses) {
            course
                    .getStudents()
                    .forEach(student -> {
                        sheet.createCell(
                                student.getFullName(),
                                course.getName(),
                                student.getAveragePerformanceByCourseName(course.getName())
                        );
                        sheet.newLine();
                    });
        }

        workBook.write(FILE_PATH);

        return FILE_PATH;
    }

}
