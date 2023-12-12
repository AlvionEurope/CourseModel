package com.courseModel.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class CreateCourseRequest {
    @NotEmpty
    private String courseName;
    @NotNull
    private float cost;
}
