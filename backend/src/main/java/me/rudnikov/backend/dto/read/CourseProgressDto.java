package me.rudnikov.backend.dto.read;

import me.rudnikov.backend.dto.serializer.CourseProgressSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonSerialize(
        using = CourseProgressSerializer.class
)
public class CourseProgressDto {
    private Long id;
    private Long studentId;
    private String studentFullName;
    private Integer studentRecordBook;
    private Long courseId;
    private String courseName;
    private String courseProfessorFullName;
    private List<Float> grades;
}