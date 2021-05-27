package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudentById(long id){
        return studentRepository.getOne(id);
    }

    public Student updateStudent(long id, Student student){
        student.setId(id);
        return saveStudent(student);
    }

    public void deleteStudent (long id){
        studentRepository.deleteById(id);
    }
}
