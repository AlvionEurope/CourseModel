package com.example.course.repository;

import com.example.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query(value = "INSERT INTO students_on_the_course (student_id, course_id) VALUES (?, ?)", nativeQuery = true)
    void addStudentCourse(Long studentId, Long courseId);

    @Modifying
    @Query(value = "DELETE FROM students_on_the_course AS sotc WHERE sotc.student_id = ? AND sotc.course_id = ?", nativeQuery = true)
    void deleteStudentCourse(Long studentId, Long courseId);
}
