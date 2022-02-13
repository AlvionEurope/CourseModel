package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CourseCompetitionRepository {

    void create(CourseCompletion completion);

    Float getAwerageGrade(String studentName);

    void deleteBy(String studentName, String courseName);

    CourseCompletion findByStudentAndCourse(String studentName, String CourseName);

    List<CourseCompletion> findAllByStudent(String studentName);

    List<String> getCurrentStudentCourses(String studentName);

    boolean canAddStudentToCourse(String studentName, String courseName);

    Float getFinalGrade(String studentName);

    void getReport(HttpServletResponse response);
}
