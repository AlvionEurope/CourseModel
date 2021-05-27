package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import java.util.List;

@Data

public class StudentProgressOnCourseDto {
    private StudentProgressOnCourseId id;
    private boolean isFinished;
    private int finalGrade;
    private String studentName;
    private String courseName;
    private List<Grade> grades;

    public StudentProgressOnCourseDto(StudentProgressOnCourse studentProgressOnCourse) {
        this.id = studentProgressOnCourse.getId();
        this.studentName = studentProgressOnCourse.getStudent().getName();
        this.courseName = studentProgressOnCourse.getCourse().getName();
        this.isFinished = studentProgressOnCourse.isFinished();
        this.finalGrade = studentProgressOnCourse.getFinalGrade();
    }
}
