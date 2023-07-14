package me.rudnikov.backend.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(
        name = "course"
)
@Table(
        name = "courses"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "course_id"
    )
    private Long id;

    @Column(
            name = "course_name"
    )
    private String name;

    @Column(
            name = "course_number"
    )
    private Integer number;

    @Column(
            name = "course_price"
    )
    private Float price;

    @ManyToOne
    private Professor professor;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
            }
    )
    @JoinTable(
            name = "courses_students",
            joinColumns = {
                    @JoinColumn (
                            name = "course_id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "student_id"
                    )
            }
    )
    private List<Student> students;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.REMOVE
    )
    private Set<CourseProgress> courseProgresses;

}