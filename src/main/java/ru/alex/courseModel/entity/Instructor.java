package ru.alex.courseModel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String phone;
    private Float payment;

    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        this.courses.add(course);
        course.getInstructors().add(this);
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getInstructors().remove(this);
    }
}
