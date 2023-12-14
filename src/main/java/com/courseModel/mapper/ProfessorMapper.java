package com.courseModel.mapper;

import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Professor;
import com.courseModel.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class ProfessorMapper {
    public Professor convert(CreateProfessorRequest request) {
        return new Professor()
                .setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setPayment(request.getPayment());
    }
    public ProfessorDTO convert(Professor professor) {
        return new ProfessorDTO()
                .setId(professor.getId())
                .setName(professor.getName())
                .setAddress(professor.getAddress())
                .setPhone(professor.getPhone())
                .setPayment(professor.getPayment());
    }
}
