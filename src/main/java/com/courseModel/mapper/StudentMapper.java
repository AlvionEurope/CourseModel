package com.courseModel.mapper;

import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Student;

import org.springframework.stereotype.Component;


@Component
public class StudentMapper {
    public Student convert(CreateStudentRequest request) {
        return new Student()
                .setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setEmail(request.getEmail());
    }
    public StudentDTO convert(Student student) {
        return new StudentDTO()
                .setGradeBook(student.getGradeBook())
                .setName(student.getName())
                .setAddress(student.getAddress())
                .setPhone(student.getPhone())
                .setEmail(student.getEmail());
    }
}
