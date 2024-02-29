package com.courseModel.repository;

import com.courseModel.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    @Query(value = "SELECT Professor.name, COUNT(Student.name) AS count_students, AVG(Teaching.final_score) AS avr_score FROM professor " +
            "LEFT JOIN Course ON Course.professor_id=Professor.id " +
            "LEFT JOIN Teaching ON Teaching.course_number=Course.number " +
            "LEFT JOIN Student ON Student.grade_book = Teaching.student_grade_book " +
            "GROUP BY Professor.name", nativeQuery = true)
    List<Map<String, String>> getReportProfessor();
}
