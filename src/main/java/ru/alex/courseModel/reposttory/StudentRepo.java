package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.model.Student;

public interface StudentRepo extends CrudRepository<Student, Long> {
}
