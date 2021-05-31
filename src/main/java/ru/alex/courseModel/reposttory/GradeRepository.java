package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
