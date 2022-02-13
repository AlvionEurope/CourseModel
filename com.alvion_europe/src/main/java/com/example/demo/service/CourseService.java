package com.example.demo.service;

import com.example.demo.entity.Course;

import java.util.List;

public interface CourseService {

    void create(Course entity);

    Course findByName (String courseName);
}
