package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TrainingCourse {

    @EmbeddedId
    private TrainingCourseId id;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    private boolean isFinished;

    @Nullable
    private Integer finalGrade = null;

    public TrainingCourse(Student student, Course course) {
        this.id = new TrainingCourseId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
        this.isFinished = false;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private List<Grade> grades = new ArrayList<>();

    public void addRelations() {
        student.getTrainingCourses().add(this);
        course.getTrainingCourses().add(this);
    }
}

