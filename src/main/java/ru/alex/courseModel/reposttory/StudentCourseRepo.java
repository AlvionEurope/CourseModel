package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.entity.StudentCourse;
import ru.alex.courseModel.entity.StudentCourseId;

public interface StudentCourseRepo extends CrudRepository<StudentCourse, StudentCourseId> {
}
