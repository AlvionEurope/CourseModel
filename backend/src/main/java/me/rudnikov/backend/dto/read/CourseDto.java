package me.rudnikov.backend.dto.read;

import me.rudnikov.backend.dto.serializer.CourseSerializer;

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