package ru.alvion.coursemodel.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.GradeAssignment;

import java.util.List;

@Repository
public interface GradeAssignmentRepository extends JpaRepository<GradeAssignment, Long> {
    List<GradeAssignment> findAllByCourseAssignment(CourseAssignment courseAssignment);
}
