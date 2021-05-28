package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import java.util.List;

@Data

public class AcademicPerformanceDto {
    private AcademicPerformanceId id;
    private boolean isFinished;
    private int finalGrade;
    private String studentName;
    private String courseName;
    private List<Grade> grades;

    public AcademicPerformanceDto(AcademicPerformance academicPerformance) {
        this.id = academicPerformance.getId();
        this.studentName = academicPerformance.getStudent().getName();
        this.courseName = academicPerformance.getCourse().getName();
        this.isFinished = academicPerformance.isFinished();
        this.finalGrade = academicPerformance.getFinalGrade();
    }
}
