package com.courseModel.entity;

import com.courseModel.enums.TeachingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter@Accessors(chain = true)
@ToString
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentGradeBook;
    private int courseNumber;
    @Enumerated(value = STRING)
    private TeachingStatus status;

}
