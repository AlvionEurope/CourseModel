package me.rudnikov.backend.dto.create;

import lombok.*;
import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.read.StudentDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseProgressCreateDto {
    private StudentDto student;
    private CourseDto course;
    private List<Float> grades;
}