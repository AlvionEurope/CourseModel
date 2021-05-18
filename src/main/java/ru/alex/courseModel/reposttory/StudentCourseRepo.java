package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.model.Student;
import ru.alex.courseModel.model.StudentCourse;

public interface StudentCourseRepo extends CrudRepository<StudentCourse, Long> {
}
