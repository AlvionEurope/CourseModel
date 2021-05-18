package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.model.Course;

public interface CourseRepo extends CrudRepository<Course, Long> {
}
