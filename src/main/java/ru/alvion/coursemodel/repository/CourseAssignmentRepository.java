package ru.alvion.coursemodel.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Long> {
    List<CourseAssignment> findAllByStudent(Student student);
    List<CourseAssignment> findAllByCourse(Course course);
    Optional<CourseAssignment> findFirstByCourseAndStudent(Course course, Student student);
    Long countByCourse(Course course);
}
