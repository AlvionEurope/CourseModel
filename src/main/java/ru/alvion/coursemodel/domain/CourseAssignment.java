package ru.alvion.coursemodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course_assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "final_grade")
    private Integer finalGrade;

    @OneToMany(mappedBy = "courseAssignment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"courseAssignment"}, allowSetters = true)
    private Set<GradeAssignment> gradeAssignments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"courseAssignments"}, allowSetters = true)
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties(value = {"courseAssignments", "professors"}, allowSetters = true)
    private Course course;

    public Long getId() {
        return this.id;
    }

    public CourseAssignment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFinalGrade() {
        return this.finalGrade;
    }

    public CourseAssignment finalGrade(Integer finalGrade) {
        this.setFinalGrade(finalGrade);
        return this;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Set<GradeAssignment> getGradeAssignments() {
        return this.gradeAssignments;
    }

    public void setGradeAssignments(Set<GradeAssignment> gradeAssignments) {
        if (this.gradeAssignments != null) {
            this.gradeAssignments.forEach(i -> i.setCourseAssignment(null));
        }
        if (gradeAssignments != null) {
            gradeAssignments.forEach(i -> i.setCourseAssignment(this));
        }
        this.gradeAssignments = gradeAssignments;
    }

    public CourseAssignment gradeAssignments(Set<GradeAssignment> gradeAssignments) {
        this.setGradeAssignments(gradeAssignments);
        return this;
    }

    public CourseAssignment addGradeAssignment(GradeAssignment gradeAssignment) {
        this.gradeAssignments.add(gradeAssignment);
        gradeAssignment.setCourseAssignment(this);
        return this;
    }

    public CourseAssignment removeGradeAssignment(GradeAssignment gradeAssignment) {
        this.gradeAssignments.remove(gradeAssignment);
        gradeAssignment.setCourseAssignment(null);
        return this;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseAssignment student(Student student) {
        this.setStudent(student);
        return this;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseAssignment course(Course course) {
        this.setCourse(course);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseAssignment)) {
            return false;
        }
        return id != null && id.equals(((CourseAssignment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CourseAssignment{" +
                "id=" + getId() +
                ", finalGrade=" + getFinalGrade() +
                "}";
    }
}
