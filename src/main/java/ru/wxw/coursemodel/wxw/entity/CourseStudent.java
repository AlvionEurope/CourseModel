package ru.wxw.coursemodel.wxw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "coursestudent")
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName ="id")
    private Course course;

    @Column(name = "student_grade_book")
    private int studentGradeBook;

    @Column(name = "is_finished")
    boolean isFinished;

    public CourseStudent(Course course, int studentGradeBook, boolean isFinished) {
        this.course = course;
        this.studentGradeBook = studentGradeBook;
        this.isFinished = isFinished;
    }

    public CourseStudent() {
    }

    public boolean isFinished() {
        return isFinished;
    }
}
