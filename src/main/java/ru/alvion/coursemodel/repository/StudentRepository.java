package ru.alvion.coursemodel.repository;

import org.springframework.data.jpa.repository.*;
import ru.alvion.coursemodel.domain.Student;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
