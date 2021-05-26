package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.entity.ActiveCourse;
import ru.alex.courseModel.entity.StudentCourseId;

public interface ActiveCourseRepo extends CrudRepository<ActiveCourse, StudentCourseId> {
}
