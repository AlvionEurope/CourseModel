package com.example.demo.service;

import com.example.demo.entity.Teacher;
import com.example.demo.exeption.ExeptionMessage;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void create(Teacher entity) {
        if (entity != null) {
            teacherRepository.create(entity);
        } else {
            throw new ExeptionMessage("Нужно заполнить поля!");
        }
    }
}
