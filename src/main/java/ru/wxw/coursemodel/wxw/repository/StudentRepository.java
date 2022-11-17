package ru.wxw.coursemodel.wxw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wxw.coursemodel.wxw.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
