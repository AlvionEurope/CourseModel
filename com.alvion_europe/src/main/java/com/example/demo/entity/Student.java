package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private String name;
    private String address;
    private String tel;
    private String email;
    private Integer recordNumber;
    private Float averagePerformance;

}
