package ru.alex.courseModel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    @Column(name = "grade_book")
    private int gradeBook;

    @Column(name = "academic_perfomance")
    private float academicPerfomance;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<StudentCourse> studentCourses = new ArrayList<>();

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
}
