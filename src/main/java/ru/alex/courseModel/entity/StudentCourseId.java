package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class StudentCourseId implements Serializable {
    private Long studentId;
    private Long courseId;

    public StudentCourseId(Long studentId, Long courseId) {
        super();
        this.studentId = studentId;
        this.courseId = courseId;
    }
}