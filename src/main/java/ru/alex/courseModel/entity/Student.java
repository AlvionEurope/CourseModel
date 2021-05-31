package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "grade_book", unique = true, nullable = false)
    private int gradeBook;

    @Column(name = "academic_performance")
    private float academicPerformance;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<TrainingCourse> trainingCourses = new ArrayList<>();
}