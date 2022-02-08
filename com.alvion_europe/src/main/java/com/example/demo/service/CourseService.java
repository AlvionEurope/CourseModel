package com.example.demo.service;

import com.example.demo.entity.Course;

public interface CourseService {

    void create(Course entity);

    Course findByName (String courseName);
}
