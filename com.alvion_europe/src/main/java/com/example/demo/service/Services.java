package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@org.springframework.stereotype.Service
public interface Services {


     void addStudents(Student student);

     void removeStudents(int id);

     void writeCourse();

     List<Course> getCourse(int id);

     void getAverageBall();

     void getFinalBall();

}
