package ru.wxw.coursemodel.wxw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wxw.coursemodel.wxw.entity.CourseStudent;
@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
}
