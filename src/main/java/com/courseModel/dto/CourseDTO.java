package com.courseModel.dto;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDTO {
    private int courseNumber;
    private String courseName;
    private float cost;
}
