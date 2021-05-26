package ru.alex.courseModel.reposttory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alex.courseModel.entity.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {
}
