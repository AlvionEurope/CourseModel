package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import com.example.demo.exeption.ExeptionMessage;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void create(Course entity) {
        Course course = courseRepository.findByName(entity.getName());
        if (entity != null) {
            courseRepository.create(entity);
        } else {
            throw new ExeptionMessage("Такой курс уже существует!");
        }
    }

    @Override
    public Course findByName(String courseName) {
        return courseRepository.findByName(courseName);
    }

}
