package ru.alvion.coursemodel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class GradeAssignmentDTO implements Serializable {

    private Long id;

    private Integer grade;

    private Instant assignmentDate;

    private CourseAssignmentDTO courseAssignment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Instant getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Instant assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public CourseAssignmentDTO getCourseAssignment() {
        return courseAssignment;
    }

    public void setCourseAssignment(CourseAssignmentDTO courseAssignment) {
        this.courseAssignment = courseAssignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GradeAssignmentDTO)) {
            return false;
        }

        GradeAssignmentDTO gradeAssignmentDTO = (GradeAssignmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, gradeAssignmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "GradeAssignmentDTO{" +
            "id=" + getId() +
            ", grade=" + getGrade() +
            ", assignmentDate='" + getAssignmentDate() + "'" +
            ", courseAssignment=" + getCourseAssignment() +
            "}";
    }
}
