package ru.wxw.coursemodel.wxw.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "cost")
    private float cost;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseStage> courseStageList;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseStudent> courseStudentList;
}
