package com.courseModel.service;

import com.courseModel.dto.CreateProfessorRequest;
import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.ProfessorDTO;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Course;
import com.courseModel.entity.Professor;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.ProfessorMapper;
import com.courseModel.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    final ProfessorRepository professorRepository;
    final ProfessorMapper mapper;

    @Override
    public ProfessorDTO create(CreateProfessorRequest request) {
        Professor professor = mapper.convert(request);
        return mapper.convert(professorRepository.save(professor));
    }

    @Override
    public ProfessorDTO readById(int id) {
        return mapper.convert(getProfessor(id));
    }

    @Override
    public ProfessorDTO updateById(int id, CreateProfessorRequest request) {
        Professor professor = getProfessor(id);
        professor.setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setPayment(request.getPayment());
        return mapper.convert(professorRepository.save(professor));
    }

    @Override
    public boolean deleteById(int id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Professor getProfessor(int id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Профессор с номером %d не найден", id)));
    }
}
