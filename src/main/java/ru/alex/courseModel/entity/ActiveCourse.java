package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ActiveCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    private boolean isFinished;
    private int finalGrade;


    public ActiveCourse(Student student, Course course) {

        this.id = new StudentCourseId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
        this.isFinished = false;
        this.finalGrade = 0;

        student.getActiveCourses().add(this);
        course.getActiveCourses().add(this);
    }

    public void removeCourse(Student student, Course course) {
        student.getActiveCourses().remove(this);
        course.getActiveCourses().remove(this);
    }

    @OneToMany(mappedBy = "activeCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();
    public void addGrade(Grade grade) {
        this.grades.add(grade);
        grade.setActiveCourse(this);
    }

    public float getAverageGrade(){
        if (isFinished()){
            return this.finalGrade;
        }
        return 1f * this.getGrades().stream().mapToInt(Grade::getValue).sum() / this.getGrades().size();
    }

}

