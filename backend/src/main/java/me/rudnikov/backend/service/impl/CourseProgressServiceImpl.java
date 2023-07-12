package me.rudnikov.backend.service.impl;

import lombok.AllArgsConstructor;
import me.rudnikov.backend.dto.create.CourseProgressCreateDto;
import me.rudnikov.backend.dto.read.CourseProgressDto;
import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.CourseProgress;
import me.rudnikov.backend.exception.ResourceAlreadyExistsException;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.CourseProgressRepository;
import me.rudnikov.backend.service.CourseProgressService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseProgressServiceImpl implements CourseProgressService {

    private final CourseProgressRepository courseProgressRepository;
    private final ModelMapper modelMapper;

    private final Integer PAGE_SIZE = 25;

    @Override
    public Long createCourseProgress(CourseProgressCreateDto dto) {

        Course mapped = modelMapper.map(dto.getCourse(), Course.class);

        if (courseProgressRepository.findByCourse(mapped).isPresent()) {
            throw new ResourceAlreadyExistsException("Course progress linked with that course already exists");
        }

        CourseProgress toSave = modelMapper.map(CourseProgressDto.class, CourseProgress.class);

        return courseProgressRepository.save(toSave).getId();
    }

    @Override
    public CourseProgressDto readCourseProgressById(Long id) {
        return courseProgressRepository.findById(id)
                .map(courseProgress -> modelMapper.map(courseProgress, CourseProgressDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Course progress with that id not found"));
    }

    @Override
    public List<CourseProgressDto> readAllCourseProgresses() {
        return courseProgressRepository.findAll(Pageable.ofSize(PAGE_SIZE))
                .map(courseProgress -> modelMapper.map(courseProgress, CourseProgressDto.class))
                .toList();
    }

    @Override
    public Boolean updateCourseProgressById(Long id, CourseProgressDto dto) {

        CourseProgress toUpdate = courseProgressRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course progress with that id not found"));

        // TODO: Implement courseProgress update

        courseProgressRepository.save(toUpdate);

        return true;
    }

    @Override
    public Boolean deleteCourseProgressById(Long id) {

        CourseProgress toDelete = courseProgressRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course progress with that id not found"));

        courseProgressRepository.delete(toDelete);

        return true;
    }

}