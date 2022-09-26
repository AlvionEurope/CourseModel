package ru.alvion.coursemodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Professor implements Serializable {

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

    @Column(name = "payment")
    private Float payment;

    @ManyToMany
    @JoinTable(
        name = "rel_professor__course",
        joinColumns = @JoinColumn(name = "professor_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "courseAssignments", "professors" }, allowSetters = true)
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Professor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Professor fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddres() {
        return this.fullAddres;
    }

    public Professor fullAddres(String fullAddres) {
        this.setFullAddres(fullAddres);
        return this;
    }

    public void setFullAddres(String fullAddres) {
        this.fullAddres = fullAddres;
    }

    public String getPhone() {
        return this.phone;
    }

    public Professor phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getPayment() {
        return this.payment;
    }

    public Professor payment(Float payment) {
        this.setPayment(payment);
        return this;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Professor courses(Set<Course> courses) {
        this.setCourses(courses);
        return this;
    }

    public Professor addCourse(Course course) {
        this.courses.add(course);
        course.getProfessors().add(this);
        return this;
    }

    public Professor removeCourse(Course course) {
        this.courses.remove(course);
        course.getProfessors().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Professor)) {
            return false;
        }
        return id != null && id.equals(((Professor) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Professor{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", fullAddres='" + getFullAddres() + "'" +
            ", phone='" + getPhone() + "'" +
            ", payment=" + getPayment() +
            "}";
    }
}
