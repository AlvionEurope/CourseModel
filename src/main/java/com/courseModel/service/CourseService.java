package com.courseModel.service;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;

public interface CourseService {
    CourseDTO create(CreateCourseRequest courseRequest);

    CourseDTO readByCourseNumber(int courseNumber);

    CourseDTO updateByCourseNumber(int courseNumber, CreateCourseRequest request);

    boolean deleteByCourseNumber(int courseNumber);

    void addStudent(int courseNumber, int gradeBook);

    void deleteStudent(int courseNumber, int gradeBook);
}
