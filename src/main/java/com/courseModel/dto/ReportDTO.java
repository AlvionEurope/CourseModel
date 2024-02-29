package com.courseModel.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReportDTO {
    private String nameProfessor;
    private String quantityStudent;
    private String avgGrade;
}
