package ru.alvion.coursemodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.repository.StudentRepository;
import ru.alvion.coursemodel.service.dto.StudentDTO;
import ru.alvion.coursemodel.service.mapper.CourseAssignmentMapper;
import ru.alvion.coursemodel.service.mapper.CourseMapper;
import ru.alvion.coursemodel.service.mapper.StudentMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final GradeAssignmentService gradeAssignmentService;
    private final CourseAssignmentService courseAssignmentService;

    private final StudentMapper studentMapper;
    private final CourseAssignmentMapper courseAssignmentMapper;

    public StudentService(StudentRepository studentRepository, GradeAssignmentService gradeAssignmentService, CourseAssignmentService courseAssignmentService, StudentMapper studentMapper, CourseMapper courseMapper, CourseAssignmentMapper courseAssignmentMapper) {
        this.studentRepository = studentRepository;
        this.gradeAssignmentService = gradeAssignmentService;
        this.courseAssignmentService = courseAssignmentService;
        this.studentMapper = studentMapper;
        this.courseAssignmentMapper = courseAssignmentMapper;
    }

    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public StudentDTO update(StudentDTO studentDTO) {
        log.debug("Request to update Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    public Optional<StudentDTO> partialUpdate(StudentDTO studentDTO) {
        log.debug("Request to partially update Student : {}", studentDTO);

        return studentRepository
                .findById(studentDTO.getId())
                .map(existingStudent -> {
                    studentMapper.partialUpdate(existingStudent, studentDTO);

                    return existingStudent;
                })
                .map(studentRepository::save)
                .map(studentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        log.debug("Request to get all Students");
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id).map(studentMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }

}
