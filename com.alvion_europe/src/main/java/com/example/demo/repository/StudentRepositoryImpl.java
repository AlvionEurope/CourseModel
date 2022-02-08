package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    List<Student> studentList = new ArrayList<>();

    @Override
    public void create(Student student) {
        studentList.add(student);
    }

    @Override
    public Student findByName(String studentName) {
        return studentList.stream().filter(student -> student.getName().equals(studentName)).findFirst().get();
    }

}
