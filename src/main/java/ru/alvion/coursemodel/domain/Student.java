package ru.alvion.coursemodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_addres")
    private String fullAddres;

    @Column(name = "phone")
    private String phone;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "record_book")
    private Integer recordBook;

    @Column(name = "gpa")
    private Float gpa;

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "gradeAssignments", "student", "course" }, allowSetters = true)
    private Set<CourseAssignment> courseAssignments = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Student id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Student fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddres() {
        return this.fullAddres;
    }

    public Student fullAddres(String fullAddres) {
        this.setFullAddres(fullAddres);
        return this;
    }

    public void setFullAddres(String fullAddres) {
        this.fullAddres = fullAddres;
    }

    public String getPhone() {
        return this.phone;
    }

    public Student phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return this.eMail;
    }

    public Student eMail(String eMail) {
        this.seteMail(eMail);
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getRecordBook() {
        return this.recordBook;
    }

    public Student recordBook(Integer recordBook) {
        this.setRecordBook(recordBook);
        return this;
    }

    public void setRecordBook(Integer recordBook) {
        this.recordBook = recordBook;
    }

    public Float getGpa() {
        return this.gpa;
    }

    public Student gpa(Float gpa) {
        this.setGpa(gpa);
        return this;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Set<CourseAssignment> getCourseAssignments() {
        return this.courseAssignments;
    }

    public void setCourseAssignments(Set<CourseAssignment> courseAssignments) {
        if (this.courseAssignments != null) {
            this.courseAssignments.forEach(i -> i.setStudent(null));
        }
        if (courseAssignments != null) {
            courseAssignments.forEach(i -> i.setStudent(this));
        }
        this.courseAssignments = courseAssignments;
    }

    public Student courseAssignments(Set<CourseAssignment> courseAssignments) {
        this.setCourseAssignments(courseAssignments);
        return this;
    }

    public Student addCourseAssignment(CourseAssignment courseAssignment) {
        this.courseAssignments.add(courseAssignment);
        courseAssignment.setStudent(this);
        return this;
    }

    public Student removeCourseAssignment(CourseAssignment courseAssignment) {
        this.courseAssignments.remove(courseAssignment);
        courseAssignment.setStudent(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", fullAddres='" + getFullAddres() + "'" +
            ", phone='" + getPhone() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", recordBook=" + getRecordBook() +
            ", gpa=" + getGpa() +
            "}";
    }
}
