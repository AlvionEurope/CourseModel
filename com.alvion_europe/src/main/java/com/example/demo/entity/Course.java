package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String name;
    private Integer number;
    private Float cost;

//    List<Student> list = new ArrayList<>();
//
//    public void addStudents(Student student) {
//        list.add(student);
//    }
//
//    public void removeStudents(int id) {
//
//    }

}
