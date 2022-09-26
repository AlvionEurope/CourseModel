package ru.alvion.coursemodel.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.alvion.coursemodel.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {}
