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
    @JoinTable(name = "auto_professor_course",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "professor_id")})
    private Set<Professor> professors = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<ActiveCourse> activeCourses = new ArrayList<>();

    public void addProfessor(Professor professor){
        this.professors.add(professor);
        professor.getCourses().add(this);
    }

    public void removeProfessor(Professor professor){
        this.professors.remove(professor);
        professor.getCourses().remove(this);
    }

    public boolean isPresent (long studentId){
        for (ActiveCourse activeCourse : activeCourses){
            if (activeCourse.getStudent().getId() == studentId){
                return true;
            }
        }
        return false;
    }
}