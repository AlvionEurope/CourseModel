package ru.alex.courseModel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student get(long id) {
        return studentRepository.getOne(id);
    }

    public void update(long id, Student student) {
        student.setId(id);
        save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Course> getStudentCourses(long studentId) {
        return courseRepository.findAllByTrainingCourses_Student_Id(studentId);
    }
}
