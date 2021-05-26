package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return (List<Student>)studentRepo.findAll();
    }

    public Student getStudentById(long id){
        return studentRepo.findById(id).get();
    }

    public Student updateStudent(long id, Student student){
        student.setId(id);
        return saveStudent(student);
    }

    public void deleteStudent (long id){
        studentRepo.deleteById(id);
    }
}
