package ru.wxw.coursemodel.wxw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AcademicPerformanceDTO {
    private float academicPerformance;
    private Long courseId;
    private Long studentId;
}
