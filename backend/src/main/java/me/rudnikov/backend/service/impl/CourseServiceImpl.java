package me.rudnikov.backend.service.impl;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.dto.create.CourseCreateDto;
import me.rudnikov.backend.dto.read.CourseDto;
//import me.rudnikov.backend.dto.mapper.CourseMapper;
//import me.rudnikov.backend.dto.mapper.StudentMapper;
import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.Professor;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.CourseRepository;
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
//    private final CourseMapper courseMapper;
//    private final StudentMapper studentMapper;
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
    public Boolean updateCourseById(Long id, CourseDto dto) {

        Course toUpdate = courseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        // TODO: Implement course update

        courseRepository.save(toUpdate);

        return true;
    }

    @Override
    public Boolean deleteCourseById(Long id) {

        Course toDelete = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        courseRepository.delete(toDelete);

        return true;
    }
}