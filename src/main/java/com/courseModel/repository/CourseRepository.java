package com.courseModel.repository;

import com.courseModel.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByProfessorId(int professorId);
}
