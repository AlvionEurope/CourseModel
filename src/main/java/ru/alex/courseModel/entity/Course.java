package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private float cost;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "auto_instructor_course",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id")})
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public void addInstructor(Instructor instructor){
        this.instructors.add(instructor);
        instructor.getCourses().add(this);
    }

    public void removeInstructor(Instructor instructor){
        this.instructors.remove(instructor);
        instructor.getCourses().remove(this);
    }
}