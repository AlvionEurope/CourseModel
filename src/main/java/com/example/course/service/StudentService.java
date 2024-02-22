package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.model.Student;

import java.util.List;

public interface StudentService {

    Student postStudent(Student student);

    Student getStudentId(Long id);

    void deleteStudent(Long id);

    Student putStudent(Student student, Long id) throws Exception;

    List<Course> canSignUpCourse(Long studentId);

    List<Course> getListCourseTaken(Long studentId);

    Integer getAvgPointCourse(Long courseId, Long StudentId);

    List<Integer> getPointCourse(Long courseId, Long StudentId);

    void addPointCourse(Long studentId, Long courseId, Integer point);

    Integer getFinalPointCourse(Long courseId, Long studentId);
}
