package ru.wxw.coursemodel.wxw.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Repotr {
    @Id
    private Long id;
    @Column(name = "nameProfessor")
    private String nameProfessor;
    @Column(name = "sumStudentAllCourse")
    private int sumStudentAllCourse;
    @Column(name = "averageStageAllStudent")
    private float averageStageAllStudent;
}
