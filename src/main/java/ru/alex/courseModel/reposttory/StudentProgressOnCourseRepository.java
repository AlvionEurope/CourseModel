package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.StudentProgressOnCourse;
import ru.alex.courseModel.entity.StudentProgressOnCourseId;

import java.util.List;

@Repository
public interface StudentProgressOnCourseRepository extends JpaRepository<StudentProgressOnCourse, StudentProgressOnCourseId> {
    List<StudentProgressOnCourse> findAllByStudent_Id(long studentId);
    List<StudentProgressOnCourse> findAllByCourse_Id(int courseId);
    List<StudentProgressOnCourse> findAllByIsFinishedIsAndStudent_Id(boolean isFinished, long studentId);
}

