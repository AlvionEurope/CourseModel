package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exeption.ExeptionMessage;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student findByName(String studentName) {
        return studentRepository.findByName(studentName);
    }

    @Override
    public void create(Student entity) {
        Student studentName = studentRepository.findByName(entity.getName());
        if (studentName.getName().equals(entity.getName())) {
            throw new ExeptionMessage("Студент с таким именем уже существует!");
        }
        studentRepository.create(entity);
    }
}
