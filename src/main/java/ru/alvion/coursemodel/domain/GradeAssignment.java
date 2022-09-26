package ru.alvion.coursemodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "grade_assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GradeAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "assignment_date")
    private Instant assignmentDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "gradeAssignments", "student", "course" }, allowSetters = true)
    private CourseAssignment courseAssignment;

    public Long getId() {
        return this.id;
    }

    public GradeAssignment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public GradeAssignment grade(Integer grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Instant getAssignmentDate() {
        return this.assignmentDate;
    }

    public GradeAssignment assignmentDate(Instant assignmentDate) {
        this.setAssignmentDate(assignmentDate);
        return this;
    }

    public void setAssignmentDate(Instant assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public CourseAssignment getCourseAssignment() {
        return this.courseAssignment;
    }

    public void setCourseAssignment(CourseAssignment courseAssignment) {
        this.courseAssignment = courseAssignment;
    }

    public GradeAssignment courseAssignment(CourseAssignment courseAssignment) {
        this.setCourseAssignment(courseAssignment);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GradeAssignment)) {
            return false;
        }
        return id != null && id.equals(((GradeAssignment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "GradeAssignment{" +
            "id=" + getId() +
            ", grade=" + getGrade() +
            ", assignmentDate='" + getAssignmentDate() + "'" +
            "}";
    }
}
