package com.courseModel.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class TeachingToScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int teachingId;
    @CreatedDate
    private java.sql.Date date;
    private int score;
}
