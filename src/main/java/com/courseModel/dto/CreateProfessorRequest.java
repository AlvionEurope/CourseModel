package com.courseModel.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Data
public class CreateProfessorRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phone;
    @PositiveOrZero
    private float payment;
}
