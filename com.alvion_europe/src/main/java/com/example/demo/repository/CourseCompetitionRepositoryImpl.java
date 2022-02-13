package com.example.demo.repository;


import com.example.demo.entity.CourseCompletion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CourseCompetitionRepositoryImpl implements CourseCompetitionRepository{

    private List<CourseCompletion> entities = new ArrayList<>();

    public void create(CourseCompletion completion) {
        entities.add(completion);
    }

    public Float getAwerageGrade(String studentName) {
        List<CourseCompletion> completionList = findAllByStudent(studentName);
        int sum = 0;
        List<Integer> grades = new ArrayList<>();
        for (CourseCompletion comp : completionList) {
            grades = comp.getGrades();
            for (Integer grade : grades) {
                sum += grade;
            }
        }

        float average = sum / grades.size();
        return average;
    }

    @Override
    public void deleteBy(String studentName, String courseName) {
        CourseCompletion current = findByStudentAndCourse(studentName, courseName);
        entities.remove(current);
    }

    @Override
    public CourseCompletion findByStudentAndCourse(String studentName, String courseName) {
        CourseCompletion current = entities
                .stream()
                .filter(competition -> competition.getStudentName()
                        .equals(studentName) && competition.getCourseName()
                        .equals(courseName)).findFirst().orElse(null);

        return current;
    }

    @Override
    public List<CourseCompletion> findAllByStudent(String studentName) {
        List<CourseCompletion> current = entities
                .stream()
                .filter(competition -> competition.getStudentName().equals(studentName)).collect(Collectors.toList());

        return current;
    }

    @Override
    public List<String> getCurrentStudentCourses(String studentName) {
        List<CourseCompletion> competitions = findAllByStudent(studentName);
        List<String> courses = competitions
                .stream()
                .map(competition -> competition.getCourseName())
                .collect(Collectors.toList());
        return courses;
    }

    // может записаться (записан ли студент на конкретный курс)
    public boolean canAddStudentToCourse(String studentName, String courseName) {
        CourseCompletion currentCompetition = findByStudentAndCourse(studentName, courseName);
        if (currentCompetition != null) {
            return false;
        } else {
            return true;
        }
    }

    public Float getFinalGrade(String studentName) {
        Float average = getAwerageGrade(studentName);
        return average;
    }
    //отчет
    public void getReport(HttpServletResponse response) {
        List<CourseCompletion> completion = entities.stream().collect(Collectors.toList());
        Row row;
        Workbook wb = new XSSFWorkbook();
        int rownum = 0;
        Sheet sheet = wb.createSheet("ReportAboutTeachers");
        row = sheet.createRow(rownum);
        Cell cell1 = row.createCell(0, CellType.STRING);
        cell1.setCellValue("Teacher");
        Cell cell2 = row.createCell(1, CellType.STRING);
        cell2.setCellValue("countStudent");
        Cell cell3 = row.createCell(2, CellType.STRING);
        cell3.setCellValue("grades");
        int sum = 0;
        String name = "";
        for (CourseCompletion comp : completion) {
            rownum++;
            row = sheet.createRow(rownum);
             Cell cell4 = row.createCell(0, CellType.STRING);
             cell4.setCellValue(comp.getTeacherName());
             name = comp.getTeacherName();
             if (name.equals(comp.getTeacherName())) {
                 Cell cell5 = row.createCell(1, CellType.NUMERIC);
                 cell5.setCellValue(++sum);
             } else {
                 break;
             }

            sum = 0;
            Cell cell6 = row.createCell(2, CellType.NUMERIC);
            cell6.setCellValue(getFinalGrade(comp.getStudentName()));
        }

        try {
            wb.write(new FileOutputStream("MyXLSX/Report.xlsx"));
            response.reset();
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename MyXLSX/Report.xlsx";

            response.setHeader(headerKey,headerValue);
            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream outputStream = response.getOutputStream();
           BufferedInputStream in = new BufferedInputStream(new FileInputStream("MyXLSX/Report.xlsx"));
            int b = 0;
            while ((b = in.read())!=-1) {
              outputStream.write(b);
            }
            wb.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
