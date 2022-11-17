package ru.wxw.coursemodel.wxw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wxw.coursemodel.wxw.entity.Professor;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
