package com.courseModel.dto;


import com.courseModel.enums.TeachingStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TeachingDTO {
    private int studentGradeBook;
    private int courseNumber;
    private TeachingStatus status;
    private List<Integer> scores;
    private Integer finalScore;
}
