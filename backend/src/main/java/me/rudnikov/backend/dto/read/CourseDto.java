package me.rudnikov.backend.dto.read;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import me.rudnikov.backend.dto.serializer.CourseSerializer;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonSerialize(
        using = CourseSerializer.class
)
public class CourseDto {
        private Long id;
        private String name;
        private Integer number;
        private Float price;
        private ProfessorDto professor;
        private List<StudentDto> students;
}