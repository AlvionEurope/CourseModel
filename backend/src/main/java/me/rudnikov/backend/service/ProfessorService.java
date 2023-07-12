package me.rudnikov.backend.service;

import me.rudnikov.backend.dto.create.ProfessorCreateDto;
import me.rudnikov.backend.dto.read.ProfessorDto;

import java.util.List;

public interface ProfessorService {
    Long createProfessor(ProfessorCreateDto dto);
    ProfessorDto readProfessorById(Long id);
    List<ProfessorDto> readAllProfessors();
    Boolean updateProfessorById(Long id, ProfessorDto dto);
    Boolean deleteProfessorById(Long id);
}