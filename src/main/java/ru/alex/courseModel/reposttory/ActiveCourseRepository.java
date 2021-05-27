package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.ActiveCourse;
import ru.alex.courseModel.entity.ActiveCourseId;

@Repository
public interface ActiveCourseRepository extends JpaRepository<ActiveCourse, ActiveCourseId> {
}
