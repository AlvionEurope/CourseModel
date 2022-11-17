package ru.wxw.coursemodel.wxw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalGradeDTO {
    private String message;
    private float finalGrade;
    private Long courseId;
    private Long studentId;

    public FinalGradeDTO(String message, float finalGrade, Long courseId, Long studentId) {
        this.message = message;
        this.finalGrade = finalGrade;
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
