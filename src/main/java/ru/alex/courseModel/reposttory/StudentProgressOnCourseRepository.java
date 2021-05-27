package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.StudentProgressOnCourse;
import ru.alex.courseModel.entity.StudentProgressOnCourseId;

@Repository
public interface StudentProgressOnCourseRepository extends JpaRepository<StudentProgressOnCourse, StudentProgressOnCourseId> {
}
