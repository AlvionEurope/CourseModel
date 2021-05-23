package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.entity.Instructor;

public interface InstructorRepo extends CrudRepository<Instructor, Long> {
}
