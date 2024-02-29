package com.courseModel.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfessorDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private float payment;
}
