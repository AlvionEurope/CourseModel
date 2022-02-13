package com.example.demo.repository;

import com.example.demo.entity.Course;

import java.util.List;

public interface CourseRepository {

    void create(Course entity);

    Course findByName (String courseName);

}