package com.courseModel.dto;

import com.courseModel.entity.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProfessorDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private float payment;

}
