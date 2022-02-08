package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCompletion {
    String studentName;
    String courseName;
    String teacherName;
    List<Integer> grades;

    public CourseCompletion(String studentName, String courseName, List<Integer> grades) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.grades = grades;
    }
}
