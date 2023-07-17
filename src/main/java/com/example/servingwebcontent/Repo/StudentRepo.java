package com.example.servingwebcontent.Repo;

import com.example.servingwebcontent.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student,Long> {
}
