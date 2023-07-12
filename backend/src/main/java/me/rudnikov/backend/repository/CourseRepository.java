package me.rudnikov.backend.repository;

import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.Professor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByNameAndNumber(String name, Integer number);
    List<Course> findByProfessorId(Long id, Pageable pageable);

}