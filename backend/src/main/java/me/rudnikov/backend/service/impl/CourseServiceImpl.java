package me.rudnikov.backend.service.impl;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.dto.create.CourseCreateDto;
import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.update.CourseUpdateDto;
import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.CourseRepository;
import me.rudnikov.backend.repository.ProfessorRepository;
import me.rudnikov.backend.repository.StudentRepository;
import me.rudnikov.backend.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    private final Integer PAGE_SIZE = 25;

    @Override
    public Long createCourse(CourseCreateDto dto) {

        if (courseRepository.findByNameAndNumber(
                dto.getName(),
                dto.getNumber()
        ).isPresent()) {
            throw new ResourceAlreadyExistsException("Course with that name and number already exists");
        }

        Course toSave = modelMapper.map(CourseDto.class, Course.class);

        return courseRepository.save(toSave).getId();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public CourseDto readCourseById(Long id) {
        return courseRepository.findById(id)
                .map(course -> modelMapper.map(course, CourseDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public List<CourseDto> readAllCourses() {
        return courseRepository.findAll(Pageable.ofSize(PAGE_SIZE))
                .map(course -> modelMapper.map(course, CourseDto.class))
                .toList();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true
    )
    public List<CourseDto> readAllCoursesByProfessorId(Long id) {
        return courseRepository.findByProfessorId(id, Pageable.ofSize(PAGE_SIZE))
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public Boolean updateCourseById(Long id, CourseUpdateDto dto) {
        Course toUpdate = courseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        if (dto.getName() != null) {
            toUpdate.setName(dto.getName());
        }
        if (dto.getNumber() != null) {
            toUpdate.setNumber(dto.getNumber());
        }
        if (dto.getPrice() != null) {
            toUpdate.setPrice(dto.getPrice());
        }
        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor with that id not found"));
            toUpdate.setProfessor(professor);
        }

        courseRepository.save(toUpdate);

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteCourseById(Long id) {

        Course toDelete = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        courseRepository.delete(toDelete);

        return true;
    }
}