package com.courseModel.repository;

import com.courseModel.entity.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachingRepository extends JpaRepository<Teaching,Integer> {
    Optional<Teaching> findByStudentGradeBookAndCourseNumber(int studentGradeBook,int courseNumber);
}
