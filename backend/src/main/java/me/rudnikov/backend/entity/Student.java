package me.rudnikov.backend.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Entity(
        name = "student"
)
@Table(
        name = "students"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
            name = "student_phone_number"
    )
    private String phoneNumber;

    @Column(
            name = "student_email"
    )
    private String email;

    @Column(
            name = "student_record_book_number"
    )
    private Integer recordBook;

    @Column(
            name = "student_avg_performance"
    )
    private Float averagePerformance;

    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER
    )
    private List<CourseProgress> courseProgressList;

    public void enrollToCourse(Course course) {
        course.addStudent(this);
    }

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
}