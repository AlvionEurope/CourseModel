package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import com.example.demo.entity.Student;

import java.util.List;


public interface CourseCompetitionService {

     void create(CourseCompletion completion);

     //boolean canAddStudentToCourse(String studentName, String courseName);

     Float getAwerageGrade(String studentName);

     int getFinalGrade(String studentName);

     //void addStudentToCourse(String studentName, String courseName);

      void removeStudentToCourse(String studentName, String courseName);

     List<String> getCurrentStudentCourses(String studentName);

}
