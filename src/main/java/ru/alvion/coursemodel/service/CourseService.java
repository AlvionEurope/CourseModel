package ru.alvion.coursemodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.repository.CourseRepository;
import ru.alvion.coursemodel.service.dto.CourseDTO;
import ru.alvion.coursemodel.service.mapper.CourseMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDTO save(CourseDTO courseDTO) {
        log.debug("Request to save Course : {}", courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    public CourseDTO update(CourseDTO courseDTO) {
        log.debug("Request to update Course : {}", courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    public Optional<CourseDTO> partialUpdate(CourseDTO courseDTO) {
        log.debug("Request to partially update Course : {}", courseDTO);

        return courseRepository
            .findById(courseDTO.getId())
            .map(existingCourse -> {
                courseMapper.partialUpdate(existingCourse, courseDTO);

                return existingCourse;
            })
            .map(courseRepository::save)
            .map(courseMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findAll() {
        log.debug("Request to get all Courses");
        return courseRepository.findAll().stream().map(courseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<CourseDTO> findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        return courseRepository.findById(id).map(courseMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.deleteById(id);
    }
}
