package example.dto;

import lombok.Data;

@Data
public class GradeDto {
    private Long id;

    private float grade;

    private Integer studentId;

    private Integer courseId;
}
