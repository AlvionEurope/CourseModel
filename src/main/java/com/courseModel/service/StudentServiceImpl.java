package com.courseModel.service;

import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Student;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.StudentMapper;
import com.courseModel.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;
    final StudentMapper mapper;

    @Override
    public StudentDTO create(CreateStudentRequest request) {
        Student student = mapper.convert(request);
        return mapper.convert(studentRepository.save(student));
    }


    @Override
    public StudentDTO readById(int gradeBook) {
        StudentDTO student = mapper.convert(getStudent(gradeBook));
        return student;
    }

    @Override
    public StudentDTO updateById(int gradeBook, CreateStudentRequest request) {
        Student student = getStudent(gradeBook);
        student.setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setEmail(request.getEmail());
        return mapper.convert(studentRepository.save(student));
    }

    @Override
    public boolean deleteById(int gradeBook) {
        if (studentRepository.existsById(gradeBook)) {
            studentRepository.deleteById(gradeBook);
            return true;
        }
        return false;
    }

    private Student getStudent(int gradeBook) {
        return studentRepository.findById(gradeBook)
                .orElseThrow(() -> new NotFoundException(String.format("Студент с зачетной книжкой номер %d не найден", gradeBook)));
    }
}
