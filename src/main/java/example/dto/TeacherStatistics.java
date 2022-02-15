package example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherStatistics {
    private String teacherName;

    private Float averageGrade;

    private Long studentsCount;

}
