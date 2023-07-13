package me.rudnikov.backend.entity;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity(
        name = "student"
)
@Table(
        name = "students"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "student_id"
    )
    private Long id;

    @Column(
            name = "student_full_name"
    )
    private String fullName;

    @Column(
            name = "student_address"
    )
    private String address;

    @Column(
            name = "student_phone_number",
            unique = true
    )
    private String phoneNumber;

    @Column(
            name = "student_email",
            unique = true
    )
    private String email;

    @Column(
            name = "student_record_book_number",
            unique = true
    )
    private Integer recordBook;

    @Column(
            name = "student_avg_performance"
    )
    private Float averagePerformance;

    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.REMOVE
            }
    )
    private List<CourseProgress> courseProgressList;

    public List<Course> getListOfTakenCourses() {
        return this.courseProgressList
                .stream()
                .map(CourseProgress::getCourse)
                .toList();
    }

    public Float getAveragePerformance() {
        return (float) this.courseProgressList
                .stream()
                .flatMap(courseProgress -> courseProgress.getGrades().stream())
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0F);
    }

    public Float getAveragePerformanceByCourseName(String courseName) {
        return (float) this.courseProgressList
                .stream()
                .filter(courseProgress -> courseProgress.getCourse().getName().equals(courseName))
                .flatMap(courseProgress -> courseProgress.getGrades().stream())
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0F);

    }
}