package com.courseModel.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeBook;
    private String name;
    private String address;
    private String phone;
    private String email;
}
