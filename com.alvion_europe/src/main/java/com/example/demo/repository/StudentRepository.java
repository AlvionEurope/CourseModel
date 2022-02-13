package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository {

    void create(Student entity);

    Student findByName(String studentName);

}
