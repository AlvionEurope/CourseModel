package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;

import java.util.List;


public interface CourseCompetitionService {

     boolean canAddStudentToCourse(String studentName, String courseName);

     List<Course> getCurrentStudentCourses(String studentName);

     Float getAwerageGrade(String studentName, String courseName);

     int getFinalGrade(String studentName, String courseName);

     void addStudentToCourse(String studentName, String courseName);

      void removeStudentToCourse(String studentName, String courseName);

}
