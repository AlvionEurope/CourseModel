package me.rudnikov.backend.service;

import me.rudnikov.backend.dto.create.ProfessorCreateDto;
import me.rudnikov.backend.dto.read.ProfessorDto;
import me.rudnikov.backend.dto.update.ProfessorUpdateDto;

import java.util.List;

public interface ProfessorService {
    Long createProfessor(ProfessorCreateDto dto);
    ProfessorDto readProfessorById(Long id);
    List<ProfessorDto> readAllProfessors();
    Boolean updateProfessorById(Long id, ProfessorUpdateDto dto);
    Boolean deleteProfessorById(Long id);
}