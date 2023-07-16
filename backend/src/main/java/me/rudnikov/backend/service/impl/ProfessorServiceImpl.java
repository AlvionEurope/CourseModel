package me.rudnikov.backend.service.impl;

import me.rudnikov.backend.dto.create.ProfessorCreateDto;
import me.rudnikov.backend.dto.read.ProfessorDto;
import me.rudnikov.backend.dto.update.ProfessorUpdateDto;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.ProfessorRepository;
import me.rudnikov.backend.service.ProfessorService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
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
    @Transactional(
            readOnly = true
    )
    public ProfessorDto readProfessorById(Long id) {
        return professorRepository.findById(id)
                .map(professor -> modelMapper.map(professor, ProfessorDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));
    }

    @Override
    @Transactional(
            readOnly = true
    )
    public List<ProfessorDto> readAllProfessors() {
        return professorRepository.findAll(Pageable.ofSize(PAGE_SIZE))
                .map(professor -> modelMapper.map(professor, ProfessorDto.class))
                .toList();
    }

    @Override
    @Transactional
    public Boolean updateProfessorById(Long id, ProfessorUpdateDto dto) {

        Professor toUpdate = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));

        if (dto.getFullName() != null) {
            toUpdate.setFullName(dto.getFullName());
        }
        if (dto.getAddress() != null) {
            toUpdate.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            toUpdate.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPayment() != null) {
            toUpdate.setPayment(dto.getPayment());
        }

        professorRepository.save(toUpdate);

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteProfessorById(Long id) {

        Professor toDelete = professorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));

        professorRepository.delete(toDelete);

        return true;
    }
}