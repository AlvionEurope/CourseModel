package com.courseModel.service;

import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;

public interface StudentService {
    StudentDTO create(CreateStudentRequest request);

    StudentDTO readByGradeBook(int gradeBook);

    StudentDTO updateByGradeBook(int gradeBook, CreateStudentRequest request);

    boolean deleteByGradeBook(int gradeBook);

    void signUpCourse(int courseNumber, int gradeBook);
}
