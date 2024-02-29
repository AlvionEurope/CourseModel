package com.courseModel.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentDTO {
    private int gradeBook;
    private String name;
    private String address;
    private String phone;
    private String email;
}
