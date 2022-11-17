package ru.wxw.coursemodel.wxw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentToCourseDTO {
    private СompletedСoursesDTO course;
    private int studentGradeBook;
}
