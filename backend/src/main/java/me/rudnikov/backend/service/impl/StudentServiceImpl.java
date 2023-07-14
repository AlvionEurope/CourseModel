package me.rudnikov.backend.service.impl;

import me.rudnikov.backend.dto.create.StudentCreateDto;
import me.rudnikov.backend.dto.read.StudentDto;
import me.rudnikov.backend.dto.update.StudentUpdateDto;
import me.rudnikov.backend.entity.Student;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.StudentRepository;
import me.rudnikov.backend.service.StudentService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    private final Integer PAGE_SIZE = 25;

    @Override
    public Long createStudent(StudentCreateDto dto) {
        if (studentRepository.findByPhoneNumberOrRecordBook(
                dto.getPhoneNumber(),
                dto.getRecordBook()
        ).isPresent()) {
            throw new ResourceAlreadyExistsException("Student with that credentials already exists");
        }

        Student toSave = modelMapper.map(StudentDto.class, Student.class);

        return studentRepository.save(toSave).getId();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public StudentDto readStudentById(Long id) {
        return studentRepository.findById(id)
                .map(student -> modelMapper.map(student, StudentDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Student with that id not found"));
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public List<StudentDto> readAllStudents() {
        return studentRepository.findAll(Pageable.ofSize(PAGE_SIZE))
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public List<StudentDto> readAllStudentsByFullName(String fullName) {
        return studentRepository.findAllByFullName(fullName, Pageable.ofSize(PAGE_SIZE))
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public List<StudentDto> readAllStudentsByAvgPerformance(Float averagePerformance) {
        return studentRepository.findAllByAveragePerformance(averagePerformance, Pageable.ofSize(PAGE_SIZE))
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    @Transactional
    public Boolean updateStudentById(Long id, StudentUpdateDto dto) {
        Student toUpdate = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with that id not found"));

        if (dto.getFullName() != null) {
            toUpdate.setFullName(dto.getFullName());
        }
        if (dto.getAddress() != null) {
            toUpdate.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            toUpdate.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null) {
            toUpdate.setEmail(dto.getEmail());
        }
        if (dto.getRecordBook() != null) {
            toUpdate.setRecordBook(dto.getRecordBook());
        }

        studentRepository.save(toUpdate);

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteStudentById(Long id) {
        Student toDelete = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with that id not found"));

        studentRepository.delete(toDelete);

        return true;
    }
}