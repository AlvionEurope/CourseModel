package ru.alvion.coursemodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.repository.CourseAssignmentRepository;
import ru.alvion.coursemodel.service.dto.CourseAssignmentDTO;
import ru.alvion.coursemodel.service.mapper.CourseAssignmentMapper;
import ru.alvion.coursemodel.service.mapper.CourseMapper;
import ru.alvion.coursemodel.service.mapper.StudentMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseAssignmentService {

    private final Logger log = LoggerFactory.getLogger(CourseAssignmentService.class);

    private final CourseAssignmentRepository courseAssignmentRepository;

    private final CourseAssignmentMapper courseAssignmentMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    public CourseAssignmentService(CourseAssignmentRepository courseAssignmentRepository, CourseAssignmentMapper courseAssignmentMapper, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.courseAssignmentRepository = courseAssignmentRepository;
        this.courseAssignmentMapper = courseAssignmentMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }

    public CourseAssignmentDTO save(CourseAssignmentDTO courseAssignmentDTO) {
        log.debug("Request to save CourseAssignment : {}", courseAssignmentDTO);
        CourseAssignment courseAssignment = courseAssignmentMapper.toEntity(courseAssignmentDTO);
        courseAssignment = courseAssignmentRepository.save(courseAssignment);



        return courseAssignmentMapper.toDto(courseAssignment);
    }

    public CourseAssignmentDTO update(CourseAssignmentDTO courseAssignmentDTO) {
        log.debug("Request to update CourseAssignment : {}", courseAssignmentDTO);
        CourseAssignment courseAssignment = courseAssignmentMapper.toEntity(courseAssignmentDTO);
        courseAssignment = courseAssignmentRepository.save(courseAssignment);
        return courseAssignmentMapper.toDto(courseAssignment);
    }

    public Optional<CourseAssignmentDTO> partialUpdate(CourseAssignmentDTO courseAssignmentDTO) {
        log.debug("Request to partially update CourseAssignment : {}", courseAssignmentDTO);

        return courseAssignmentRepository
                .findById(courseAssignmentDTO.getId())
                .map(existingCourseAssignment -> {
                    courseAssignmentMapper.partialUpdate(existingCourseAssignment, courseAssignmentDTO);

                    return existingCourseAssignment;
                })
                .map(courseAssignmentRepository::save)
                .map(courseAssignmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<CourseAssignmentDTO> findAll() {
        log.debug("Request to get all CourseAssignments");
        return courseAssignmentRepository
                .findAll()
                .stream()
                .map(courseAssignmentMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<CourseAssignmentDTO> findOne(Long id) {
        log.debug("Request to get CourseAssignment : {}", id);
        return courseAssignmentRepository.findById(id).map(courseAssignmentMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete CourseAssignment : {}", id);
        courseAssignmentRepository.deleteById(id);
    }

    @Transactional
    public List<CourseAssignment> findAllByStudent(Student student) {
        return courseAssignmentRepository
                .findAllByStudent(student);
    }

    @Transactional
    public boolean studentCannotAssignCourse(CourseAssignmentDTO courseAssignmentDTO) {
        Course course = courseMapper.toEntity(courseAssignmentDTO.getCourse());
        Student student = studentMapper.toEntity(courseAssignmentDTO.getStudent());
        return courseAssignmentRepository.findFirstByCourseAndStudent(course,student).isPresent();
    }
}
