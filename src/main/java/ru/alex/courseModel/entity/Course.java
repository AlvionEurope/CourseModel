package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private float cost;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "auto_instructor_course",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "instructor_id")})
    private Set<Instructor> instructors = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public void addInstructor(Instructor instructor){
        this.instructors.add(instructor);
        instructor.getCourses().add(this);
    }

    public void removeInstructor (Instructor instructor){
        this.instructors.remove(instructor);
        instructor.getCourses().remove(this);
    }

    public boolean isPresent (long studentId){
        for (StudentCourse studentCourse : studentCourses){
            if (studentCourse.getStudent().getId() == studentId){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}