package com.courseModel.repository;

import com.courseModel.entity.Teaching;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeachingRepository extends JpaRepository<Teaching, Integer> {
    Optional<Teaching> findByStudentGradeBookAndCourseNumber(int studentGradeBook, int courseNumber);

    @EntityGraph(value = "TeachingWithCourse")
    @Query("SELECT teaching FROM Teaching teaching " +
            "INNER JOIN fetch Course course ON (teaching.course.number = course.number) " +
            "WHERE teaching.status = 'FINISHED' " +
            "AND teaching.studentGradeBook = ?1")
    List<Teaching> findFinishedTeachingWithCourseByStudentGradeBook(int studentGradeBook);
}
