package ru.wxw.coursemodel.wxw.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "grade_book", nullable = false, unique = true)
    private int gradeBook;

    @Column(name = "academic_performance")
    private float academicPerformance;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<CourseStage> courseStage = new ArrayList<>();


}
