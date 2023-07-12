//package me.rudnikov.backend.service.impl;
//
//import com.github.ckpoint.toexcel.core.ToWorkBook;
//import com.github.ckpoint.toexcel.core.ToWorkSheet;
//import com.github.ckpoint.toexcel.core.type.ToWorkBookType;
//import lombok.AllArgsConstructor;
//import me.rudnikov.backend.entity.*;
//import me.rudnikov.backend.service.ExcelReportService;
//import me.rudnikov.backend.service.ProfessorService;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class ExcelReportServiceImpl implements ExcelReportService {
//
//    private final ProfessorService professorService;
//    private final ModelMapper modelMapper;
//
//    private final String FILE_PATH = "backend/src/main/resources/excel/result.xlsx";
//
//    public String createProfessorReport(Long id) throws IOException {
//
//        Professor professor = modelMapper
//                .map(professorService.readProfessorById(id), Professor.class);
//
//        List<Course> courses = professor.getCourses();
//
//        ToWorkBook workBook = new ToWorkBook(ToWorkBookType.XSSF);
//        ToWorkSheet sheet = workBook.createSheet();
//
//        sheet.createTitleCell(3, "ФИО_Преподавателя");
//
//        for (Course course : courses) {
//            sheet.createTitleCell(3, "Курс_" + course.getId(), "Количество_Студентов", "Средняя_оценка");
//        }
//
//        sheet.createCellToNewline(professor.getFullName());
//
//        for (Course course : courses) {
//            sheet.createCell(course.getName());
//            sheet.createCell(course.getStudents().size());
//
//            List<CourseProgress> progressList = course.getStudents().stream().map(student -> student.getCourseProgressList());
//
//            List<Float> floats = fl.stream().map(CourseProgress::getCurrentAverageGrade).toList();
//            floats.forEach(sheet::createCell);
////                    .forEach(courseProgress -> sheet.createCell(courseProgress));
//        }
//
//        workBook.write(FILE_PATH);
//
//        return FILE_PATH;
//    }
//
//}
