package com.example.demo.repository;

import com.example.demo.entity.Course;

public interface CourseRepository {

    void create(Course entity);

    Course findByName (String courseName);
}