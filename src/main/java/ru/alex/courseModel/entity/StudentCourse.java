package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @Column
    private boolean isFinished;
    @Column
    private int finalGrade;

    public StudentCourse (Student student, Course course) {
        // create primary key
        this.id = new StudentCourseId(student.getId(), course.getId());

        // initialize attributes
        this.student = student;
        this.course = course;
        this.isFinished = false;
        this.finalGrade = 0;

        // update relationships to assure referential integrity
        student.getStudentCourses().add(this);
        course.getStudentCourses().add(this);
    }

    public void removeCourse (Student student, Course course){
        student.getStudentCourses().remove(this);
        course.getStudentCourses().remove(this);
    }

    @OneToMany(mappedBy = "studentCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    public void addGrade(Grade grade){
        this.grades.add(grade);
        grade.setStudentCourse(this);
    }

    public void removeGrade(Grade grade){
        this.grades.remove(grade);
        grade.setStudentCourse(null);
    }
}

