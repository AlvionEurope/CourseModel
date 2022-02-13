package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository{
    private List<Course> entities = new ArrayList<>();

    @Override
    public void create(Course entity) {
       entities.add(entity);
    }

    @Override
    public Course findByName(String courseName) {
        return entities.stream().filter(entity -> entity.getName().equals(courseName)).findFirst().orElse(null);
    }

}
