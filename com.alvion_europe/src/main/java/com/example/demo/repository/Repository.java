package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;

import java.util.List;

public interface Repository {

    void addStudents(Student student);

    void removeStudents(int id);

    void writeCourse();

    List<Course> getCourse(int id);

    void getAverageBall();

    void getFinalBall();

}
