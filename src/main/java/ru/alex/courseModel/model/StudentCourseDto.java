package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentCourseDto {
    private StudentCourseId id;
    private boolean isFinished;
    private int finalGrade;
    private String studentName;
    private String courseName;
    private List<GradeDto> grades = new ArrayList<>();

    public StudentCourseDto(StudentCourse studentCourse) {
        this.id = studentCourse.getId();
        this.studentName = studentCourse.getStudent().getName();
        this.courseName = studentCourse.getCourse().getName();
        this.isFinished = studentCourse.isFinished();
        this.finalGrade = studentCourse.getFinalGrade();
    }

    public static List<GradeDto> getGrades(List<Grade> grades){
        List<GradeDto> gradeDtoList = new ArrayList<>();
        if (grades != null) {
            for (Grade grade : grades) {
                GradeDto gradeDto = new GradeDto();
                gradeDto.setId(gradeDto.getId());
                gradeDto.setValue(grade.getValue());
                gradeDtoList.add(gradeDto);
            }
        }
        return gradeDtoList;
    }



}
