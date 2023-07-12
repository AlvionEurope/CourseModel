package me.rudnikov.backend.service.impl;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.dto.create.ProfessorCreateDto;
import me.rudnikov.backend.dto.read.ProfessorDto;
//import me.rudnikov.backend.dto.mapper.CourseMapper;
//import me.rudnikov.backend.dto.mapper.ProfessorMapper;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.ProfessorRepository;
import me.rudnikov.backend.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
//    private final ProfessorMapper professorMapper;
//    private final CourseMapper courseMapper;
    private final ModelMapper modelMapper;

    private final Integer PAGE_SIZE = 25;

    @Override
    public Long createProfessor(ProfessorCreateDto dto) {

        if (professorRepository.findByPhoneNumber(
                dto.getPhoneNumber()
        ).isPresent()) {
            throw new ResourceAlreadyExistsException("Professor with that phone number already exists");
        }

        Professor toSave = modelMapper.map(ProfessorDto.class, Professor.class);

        return professorRepository.save(toSave).getId();
    }

    @Override
    public ProfessorDto readProfessorById(Long id) {
        return professorRepository.findById(id)
                .map(professor -> modelMapper.map(professor, ProfessorDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));
    }

    @Override
    public List<ProfessorDto> readAllProfessors() {
        return professorRepository.findAll(Pageable.ofSize(PAGE_SIZE))
                .map(professor -> modelMapper.map(professor, ProfessorDto.class))
                .toList();
    }

    @Override
    public Boolean updateProfessorById(Long id, ProfessorDto dto) {

        Professor toUpdate = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));

        // TODO: Implement professor update

        professorRepository.save(toUpdate);

        return true;
    }

    @Override
    public Boolean deleteProfessorById(Long id) {

        Professor toDelete = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));

        professorRepository.delete(toDelete);

        return true;
    }

}