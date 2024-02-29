package com.courseModel.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDTO {
    private int number;
    private String name;
    private float cost;
    private Integer professorId;
}
