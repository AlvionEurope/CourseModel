package me.rudnikov.backend.dto.update;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressUpdateDto {
    private Long studentId;
    private Long courseId;
    private List<Float> grades;
}