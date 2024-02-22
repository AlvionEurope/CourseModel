package com.example.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "student")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    Long id;

    String name;

    String address;

    String tel;

    String email;
    @Column(name = "record_book_number")
    Integer recordBookNumber;
    @Column(name = "avg_point")
    float avgPoint;
}
