package ru.wxw.coursemodel.wxw.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "coursestage")
public class CourseStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName ="id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName ="id")
    private Student student;
    @Column(name = "grade")
    private int grade;
}
