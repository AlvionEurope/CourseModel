package com.courseModel.repository;

import com.courseModel.entity.Professor;
import com.courseModel.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
}
