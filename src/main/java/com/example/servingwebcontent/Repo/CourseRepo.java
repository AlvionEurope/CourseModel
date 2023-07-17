package com.example.servingwebcontent.Repo;

import com.example.servingwebcontent.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course,Long> {
}
