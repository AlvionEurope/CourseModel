package me.rudnikov.backend.dto.create;

import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.read.StudentDto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseProgressCreateDto {
    private StudentDto student;
    private CourseDto course;
}