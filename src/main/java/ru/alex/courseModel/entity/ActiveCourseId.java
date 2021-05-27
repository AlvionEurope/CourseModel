package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ActiveCourseId implements Serializable {
    private long studentId;
    private int courseId;

    public ActiveCourseId(long studentId, int courseId) {
        super();
        this.studentId = studentId;
        this.courseId = courseId;
    }
}