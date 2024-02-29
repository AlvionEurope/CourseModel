package com.courseModel.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateStudentRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;
}
