package com.courseModel.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class ScoreDTO {
    @Min(value = 1,message = "оценка должна быть в диапазоне от 1 до 5")
    @Max(value = 5,message = "оценка должна быть в диапазоне от 1 до 5")
    private int score;
}
