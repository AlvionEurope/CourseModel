package com.courseModel.entity;

import com.courseModel.enums.TeachingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NamedEntityGraph(name = "TeachingWithCourse",
        attributeNodes = {
                @NamedAttributeNode(value = "course")
        }
)
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentGradeBook;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @Enumerated(value = STRING)
    private TeachingStatus status;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "teachingId",
            cascade = CascadeType.ALL)
    private List<TeachingToScore> scores;
    private Integer finalScore;
}
