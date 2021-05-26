package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class StudentCourseId implements Serializable {
    private long studentId;
    private int courseId;

    public StudentCourseId(long studentId, int courseId) {
        super();
        this.studentId = studentId;
        this.courseId = courseId;
    }
}