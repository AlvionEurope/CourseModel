package ru.alex.courseModel.service;

import ru.alex.courseModel.model.Course;
import ru.alex.courseModel.model.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    Student getById(long id);
    void add(Student student);
    Student updateStudentById(long id, Student student);
    List<Student> getAll();

}
