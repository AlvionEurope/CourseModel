package me.rudnikov.backend.repository;

import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {

    Optional<CourseProgress> findByCourse(Course course);

}