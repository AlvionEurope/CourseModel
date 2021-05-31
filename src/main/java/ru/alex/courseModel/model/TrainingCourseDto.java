package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import java.util.List;

@Data

public class TrainingCourseDto {
    private TrainingCourseId id;
    private boolean isFinished;
    private Integer finalGrade;
    private String studentName;
    private String courseName;
    private List<Grade> grades;

    public TrainingCourseDto(TrainingCourse trainingCourse) {
        this.id = trainingCourse.getId();
        this.studentName = trainingCourse.getStudent().getName();
        this.courseName = trainingCourse.getCourse().getName();
        this.isFinished = trainingCourse.isFinished();
        this.finalGrade = trainingCourse.getFinalGrade();
    }
}
