package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.exeption.ExeptionMessage;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void create(Course entity) {
        Course course = courseRepository.findByName(entity.getName());
        if (entity.getName().equals(course.getName())) {
            throw new ExeptionMessage("Такой курс уже существует!");
        }
         courseRepository.create(entity);
    }

    @Override
    public Course findByName(String courseName) {
        return courseRepository.findByName(courseName);
    }
}
