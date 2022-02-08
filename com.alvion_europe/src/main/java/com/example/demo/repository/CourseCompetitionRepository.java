package com.example.demo.repository;

import com.example.demo.entity.CourseCompletion;

import java.util.List;

public interface CourseCompetitionRepository {

    void create(CourseCompletion entity);

    void deleteBy(String studentName, String courseName);

    List<CourseCompletion> findByStudentAndCourse(String studentName, String CourseName);

    List<CourseCompletion> findAllByStudent(String studentName);
}
