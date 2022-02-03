package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private String adress;
    private String tel;
    private String email;
    private Integer recordNumber;
    private Float averagePerformance;
    private List<Course> courses;

//    public void writeCourse(){
//
//    }
//
//    public List<Course> getCourse(int id) {
//
//    }
}
