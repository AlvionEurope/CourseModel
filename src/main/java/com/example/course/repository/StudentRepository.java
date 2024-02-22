package com.example.course.repository;

import com.example.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(
            value = "SELECT point FROM student_point_course AS spc WHERE spc.course_id = ? AND spc.student_id= ?", nativeQuery = true
    )
    List<Integer> getPointCourse(Long courseId, Long studentId);

    @Query(
            value = "SELECT AVG(point) FROM student_point_course AS spc WHERE spc.course_id = ? AND spc.student_id = ?", nativeQuery = true
    )
    Integer getAvgPointCourse(Long courseId, Long studentId);

    @Query(
            value = "SELECT AVG(point) FROM student_point_course AS spc WHERE spc.student_id = ?", nativeQuery = true
    )
    Float getAvgPoint(Long studentId);

    @Query(value = "SELECT course_id FROM students_on_the_course AS sotc WHERE sotc.student_id = ?", nativeQuery = true)
    List<Long> coursesId(Long studentId);

    @Modifying
    @Query(value = "INSERT INTO student_point_course (student_id, course_id, point ) VALUES (?, ?, ?)", nativeQuery = true)
    void addPointCourse(Long studentId, Long courseId, Integer point);

    @Modifying
    @Query(value = "INSERT INTO courses_taken (student_id, course_id) VALUES (?, ?)", nativeQuery = true)
    void addListOfCoursesTaken(Long studentId, Long courseId);

    @Query(value = "SELECT course_id FROM courses_taken AS ct WHERE ct.student_id = ?", nativeQuery = true)
    List<Long> getCoursesIdOfTaken(Long studentId);

    @Modifying
    @Query(value = "DELETE FROM students_on_the_course WHERE student_id = ?", nativeQuery = true)
    void deleteFromCourse(Long studentId);
}
