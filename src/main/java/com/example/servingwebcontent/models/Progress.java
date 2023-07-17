package com.example.servingwebcontent.models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    public List<Integer> mark = new ArrayList<>();
@OneToOne
public Student student;
    @OneToOne
    public Course course;
    private float sum ;
    private int quantity;

    public Long getNum() {
        return id;
    }

    public void setNum(Long id) {
        this.id = id;
    }

    public List<Integer> getMark() {
        return mark;
    }

    public void setMark(List<Integer> mark) {
        this.mark = mark;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float avgMark(){
        sum=0;
        for(int i = 0; i< mark.size();i++) {
           sum = sum + mark.get(i);
        }
        return sum/mark.size();
    }
    public float finMark(){
        sum=0;
        for(int i = 0; i< mark.size();i++) {
            sum = sum + mark.get(i);
        }
        return sum/quantity;
    }
}