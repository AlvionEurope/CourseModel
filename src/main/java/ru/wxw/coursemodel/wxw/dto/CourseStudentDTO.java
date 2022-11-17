package ru.wxw.coursemodel.wxw.dto;

import lombok.Getter;
import lombok.Setter;
import ru.wxw.coursemodel.wxw.entity.Course;


@Getter
@Setter
public class CourseStudentDTO {

    private Long id;
    private Course course;
    private int studentGradeBook;
    private boolean isFinished;

    public CourseStudentDTO(Course course, int studentGradeBook, boolean isFinished) {
        this.course = course;
        this.studentGradeBook = studentGradeBook;
        this.isFinished = isFinished;
    }

    public CourseStudentDTO() {
    }

}
