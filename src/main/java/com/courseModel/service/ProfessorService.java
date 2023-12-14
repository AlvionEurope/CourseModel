package com.courseModel.service;

import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.dto.StudentDTO;

public interface ProfessorService {
    ProfessorDTO create(CreateProfessorRequest request);

    ProfessorDTO readById(int id);

    ProfessorDTO updateById(int id, CreateProfessorRequest request);

    boolean deleteById(int id);

}
