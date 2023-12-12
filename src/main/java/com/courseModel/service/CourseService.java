package com.courseModel.service;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {
    CourseDTO create(CreateCourseRequest courseRequest);
    CourseDTO readById(int id);
    CourseDTO updateById(int id, CreateCourseRequest request);
    boolean deleteById(int id);

    void addStudent(int courseNumber, int gradeBook);

    void deleteStudent(int courseNumber, int gradeBook);
}
