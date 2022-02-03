package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.Repository;

import java.util.List;

public class ServiceImpl implements Services {

    Repository repository;

    @Override
    public void addStudents(Student student) {
        repository.addStudents(student);
    }

    @Override
    public void removeStudents(int id) {
        repository.removeStudents(id);
    }


    public void writeCourse() {

    }

    public List<Course> getCourse(int id) {
        return repository.getCourse(id);
    }

    public void getAverageBall() {

    }

    public void getFinalBall() {

    }
}
