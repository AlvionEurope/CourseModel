package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentService {

    void create(Student entity);

    Student findByName(String studentName);
}
