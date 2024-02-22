package com.example.course.service;

import com.example.course.model.Course;

public interface CourseService {

    Course postCourse(Course course);

    Course getCourse(Long courseId);

    void deleteCourse(Long courseId);

    Course putCourse(Long courseId, Course course) throws Exception;

    void addStudentOnCourse(Long courseId, Long studentId);

    void deleteStudentCourse(Long courseId, Long studentId);
}
