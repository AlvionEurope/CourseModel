package com.courseModel.service;

import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDTO create(CreateStudentRequest request);
 //   List<StudentDTO> readAllStudents();
    StudentDTO readById(int id);
    StudentDTO updateById(int id,CreateStudentRequest request);
    boolean deleteById(int id);
}
