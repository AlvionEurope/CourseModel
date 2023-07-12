package me.rudnikov.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.List;

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
            fetch = FetchType.EAGER
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

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}