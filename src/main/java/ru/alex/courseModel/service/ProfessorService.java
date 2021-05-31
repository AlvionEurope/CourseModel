package ru.alex.courseModel.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.ProfessorRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public Professor get(long id) {
        return professorRepository.getOne(id);
    }

    public void update(long id, Professor professor) {
        professor.setId(id);
        save(professor);
    }

    public void deleteProfessor(long id) {
        professorRepository.deleteById(id);
    }

    public void assignCourse(int courseId, long instructorId) {
        Professor professor = get(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.getCourses().add(course);
        course.getProfessors().add(professor);
        save(professor);
    }

    public void removeCourse(int courseId, long instructorId) {
        Professor professor = get(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.getCourses().remove(course);
        course.getProfessors().remove(professor);
        save(professor);
    }

    public byte[] getReport() throws IOException {
        byte[] bytes;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");


        XSSFRow rowHead = sheet.createRow((short) 0);


        rowHead.createCell(0).setCellValue("Professor name");
        rowHead.createCell(1).setCellValue("Number of students");
        rowHead.createCell(2).setCellValue("Average academic performance");

        short i = 1;
        for (Professor professor : professorRepository.getAllByOrderByName()) {

            List<Student> students = studentRepository.findAllByTrainingCourses_Course_Professors_Id(professor.getId());
            float averageAcademicPerformance = (float) students
                    .stream()
                    .mapToDouble(Student::getAcademicPerformance)
                    .sum();
            XSSFRow row = sheet.createRow(i++);
            row.createCell(0).setCellValue(professor.getName());
            row.createCell(1).setCellValue(students.size());
            if (averageAcademicPerformance == 0) {
                row.createCell(2).setCellValue(0);
            } else {
                row.createCell(2).setCellValue(averageAcademicPerformance / students.size());
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);

        bytes = out.toByteArray();
        return bytes;
    }
}
