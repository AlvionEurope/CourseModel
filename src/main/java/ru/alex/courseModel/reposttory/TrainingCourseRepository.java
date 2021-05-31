package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.TrainingCourse;
import ru.alex.courseModel.entity.TrainingCourseId;

import java.util.List;

@Repository
public interface TrainingCourseRepository extends JpaRepository<TrainingCourse, TrainingCourseId> {
    List<TrainingCourse> findAllByStudent_Id(long studentId);
    List<TrainingCourse> findAllByCourse_Id(int courseId);
    List<TrainingCourse> findAllByIsFinishedIsAndStudent_Id(boolean isFinished, long studentId);
}

