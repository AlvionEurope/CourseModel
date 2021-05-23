package ru.alex.courseModel.reposttory;

import org.springframework.data.repository.CrudRepository;
import ru.alex.courseModel.entity.Grade;

public interface GradeRepo extends CrudRepository<Grade, Long> {
}
