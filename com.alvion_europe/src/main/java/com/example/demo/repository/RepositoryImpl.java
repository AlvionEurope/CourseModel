package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryImpl {

    List<Student> students = new ArrayList<>();
    List<Student> courses = new ArrayList<>();


    public void addStudents(Student student) {

        courses.add(student);
    }

    public void removeStudents(int id) {
        courses.remove(id);
    }


    public void writeCourse() {

    }

    public List<Course> getCourse(int id) {
      Student student = students.get(id);
      return student.getCourses();
    }

    public void getAverageBall() {

    }

    public void getFinalBall() {

    }

}
