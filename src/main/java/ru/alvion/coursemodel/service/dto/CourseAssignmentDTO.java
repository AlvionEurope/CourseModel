package ru.alvion.coursemodel.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class CourseAssignmentDTO implements Serializable {

    private Long id;

    private Integer finalGrade;

    private StudentDTO student;

    private CourseDTO course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseAssignmentDTO)) {
            return false;
        }

        CourseAssignmentDTO courseAssignmentDTO = (CourseAssignmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, courseAssignmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "CourseAssignmentDTO{" +
            "id=" + getId() +
            ", finalGrade=" + getFinalGrade() +
            ", student=" + getStudent() +
            ", course=" + getCourse() +
            "}";
    }
}
