package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.AcademicPerformance;
import ru.alex.courseModel.entity.AcademicPerformanceId;

import java.util.List;

@Repository
public interface AcademicPerformanceRepository extends JpaRepository<AcademicPerformance, AcademicPerformanceId> {
    List<AcademicPerformance> findAllByStudent_Id(long studentId);
    List<AcademicPerformance> findAllByCourse_Id(int courseId);
    List<AcademicPerformance> findAllByIsFinishedIsAndStudent_Id(boolean isFinished, long studentId);
}

