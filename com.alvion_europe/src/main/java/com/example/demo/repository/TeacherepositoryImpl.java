package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherepositoryImpl implements TeacherRepository{
    List<Teacher> teacherList = new ArrayList<>();

    @Override
    public void create(Teacher entity) {
        teacherList.add(entity);
    }

}
