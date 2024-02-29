package com.courseModel.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;


@Data
public class CreateCourseRequest {
    @NotEmpty
    private String name;
    @PositiveOrZero
    private float cost;
    private Integer professorId;
}
