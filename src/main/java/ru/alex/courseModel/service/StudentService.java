package ru.alex.courseModel.service;

import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student get(long id) {
        return studentRepository.getOne(id);
    }

    public Student update(long id, Student student) {
        student.setId(id);
        return save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
