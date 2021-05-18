package ru.alex.courseModel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String phone;
    private float payment;

    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses = new HashSet<>();

}
