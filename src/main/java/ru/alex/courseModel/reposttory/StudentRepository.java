package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
