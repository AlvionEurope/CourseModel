package ru.alvion.coursemodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_number")
    private Integer courseNumber;

    @Column(name = "cost")
    private Float cost;

    @OneToMany(mappedBy = "course")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"gradeAssignments", "student", "course"}, allowSetters = true)
    private Set<CourseAssignment> courseAssignments = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"courses"}, allowSetters = true)
    private Set<Professor> professors = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Course id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public Course courseName(String courseName) {
        this.setCourseName(courseName);
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseNumber() {
        return this.courseNumber;
    }

    public Course courseNumber(Integer courseNumber) {
        this.setCourseNumber(courseNumber);
        return this;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Float getCost() {
        return this.cost;
    }

    public Course cost(Float cost) {
        this.setCost(cost);
        return this;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Set<CourseAssignment> getCourseAssignments() {
        return this.courseAssignments;
    }

    public void setCourseAssignments(Set<CourseAssignment> courseAssignments) {
        if (this.courseAssignments != null) {
            this.courseAssignments.forEach(i -> i.setCourse(null));
        }
        if (courseAssignments != null) {
            courseAssignments.forEach(i -> i.setCourse(this));
        }
        this.courseAssignments = courseAssignments;
    }

    public Course courseAssignments(Set<CourseAssignment> courseAssignments) {
        this.setCourseAssignments(courseAssignments);
        return this;
    }

    public Course addCourseAssignment(CourseAssignment courseAssignment) {
        this.courseAssignments.add(courseAssignment);
        courseAssignment.setCourse(this);
        return this;
    }

    public Course removeCourseAssignment(CourseAssignment courseAssignment) {
        this.courseAssignments.remove(courseAssignment);
        courseAssignment.setCourse(null);
        return this;
    }

    public Set<Professor> getProfessors() {
        return this.professors;
    }

    public void setProfessors(Set<Professor> professors) {
        if (this.professors != null) {
            this.professors.forEach(i -> i.removeCourse(this));
        }
        if (professors != null) {
            professors.forEach(i -> i.addCourse(this));
        }
        this.professors = professors;
    }

    public Course professors(Set<Professor> professors) {
        this.setProfessors(professors);
        return this;
    }

    public Course addProfessor(Professor professor) {
        this.professors.add(professor);
        professor.getCourses().add(this);
        return this;
    }

    public Course removeProfessor(Professor professor) {
        this.professors.remove(professor);
        professor.getCourses().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", courseName='" + getCourseName() + "'" +
                ", courseNumber=" + getCourseNumber() +
                ", cost=" + getCost() +
                "}";
    }
}
