package example.repo;

import example.entity.EnrollmentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<EnrollmentGrade, Long> {

    @Query("select g from EnrollmentGrade g " +
            "join fetch g.course join fetch g.student " +
            "where g.student.id = ?1")
    List<EnrollmentGrade> findStudentGrades(int studentId);

    @Query("select g from EnrollmentGrade g " +
            "join fetch g.course join fetch g.student " +
            "where g.student.id = ?1 and g.course.id = ?2")
    List<EnrollmentGrade> findStudentGrades(int studentId, int courseId);

    @Query(value = "select avg(ss.grade) from " +
            "(select avg(ce.grade) grade from course_enrollment ce " +
            "where ce.student_id = ?1 " +
            "group by ce.course_id) ss", nativeQuery = true)
    @NonNull
    Float getAverageGrade(int studentId);

    @Query("select avg(g.grade) from EnrollmentGrade g where g.student.id = ?1 and g.course.id = ?2")
    float getAverageGrade(int studentId, int courseId);
}
