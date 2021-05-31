package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String phone;
    private Float payment;

    @ManyToMany(mappedBy = "professors")
    private List<Course> courses = new ArrayList<>();
}
