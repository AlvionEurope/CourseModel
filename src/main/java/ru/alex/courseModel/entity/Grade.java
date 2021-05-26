package ru.alex.courseModel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "grade")
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int value;

    public Grade(int value) {
        this.value = value;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private ActiveCourse activeCourse;
}
