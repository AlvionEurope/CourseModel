package com.example.demo.service;

import com.example.demo.entity.Student;

public interface StudentService {

    void create(Student entity);

    Student findByName(String studentName);
}
