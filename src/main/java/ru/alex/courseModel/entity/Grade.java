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
}
