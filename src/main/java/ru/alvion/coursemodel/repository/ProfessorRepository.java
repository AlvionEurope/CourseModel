package ru.alvion.coursemodel.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.alvion.coursemodel.domain.Professor;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
