package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
