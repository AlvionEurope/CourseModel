package me.rudnikov.backend.service.impl;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.dto.create.StudentCreateDto;
import me.rudnikov.backend.dto.read.StudentDto;
import me.rudnikov.backend.entity.Student;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.StudentRepository;
import me.rudnikov.backend.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean updateStudentById(Long id, StudentDto dto) {

        Student toUpdate = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with that id not found"));

        // TODO: Implement student update

        studentRepository.save(toUpdate);

        return true;
    }

    @Override
    public Boolean deleteStudentById(Long id) {

        Student toDelete = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with that id not found"));

        studentRepository.delete(toDelete);

        return true;
    }

}