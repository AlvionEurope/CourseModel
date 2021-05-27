package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
