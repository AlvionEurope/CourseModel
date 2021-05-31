package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    @JoinColumn(name = "student_id")
    private List<Grade> grades;

    public void addRelations() {
        student.getTrainingCourses().add(this);
        course.getTrainingCourses().add(this);
    }
}

