package com.example.course.repository;

import com.example.course.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Modifying
    @Query(value = "INSERT INTO professor_on_the_course (professor_id, course_id) VALUES (?, ?)", nativeQuery = true)
    void addProfessorCourse(Long professorId, Long courseId);

    @Query(value = "SELECT course_id FROM professor_on_the_course WHERE professor_id = ?", nativeQuery = true)
    List<Long> getListCourseId(Long professorId);

    @Query(value = "SELECT COUNT(DISTINCT student_id) FROM student_point_course WHERE course_id = ?", nativeQuery = true)
    Integer getNumberOfStudent(Long courseId);

    @Query(
            value = "SELECT AVG(point) FROM student_point_course WHERE course_id = ?", nativeQuery = true
    )
    Float getAvgPointAllStudent(Long courseId);
}
