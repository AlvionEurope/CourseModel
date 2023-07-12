package me.rudnikov.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity(
        name = "course_progress"
)
@Table(
        name = "course_progress"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseProgress {

    @Id
    @SequenceGenerator(
            name = "course_progress_sequence",
            sequenceName = "course_progress_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_progress_sequence"
    )
    @Column(
            name = "course_progress_id"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<Float> grades;

    public Float getCurrentAverageGrade(Student student) {
        return (float) this.grades
                .stream()
                .mapToDouble(Float::doubleValue)
                .average().orElse(0.0F);
    }

    public Float getFinalGrade(Student student) {
        return 0.0F;
    }
}