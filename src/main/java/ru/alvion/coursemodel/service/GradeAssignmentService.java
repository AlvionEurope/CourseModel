package ru.alvion.coursemodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.GradeAssignment;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.repository.CourseAssignmentRepository;
import ru.alvion.coursemodel.repository.GradeAssignmentRepository;
import ru.alvion.coursemodel.repository.StudentRepository;
import ru.alvion.coursemodel.service.dto.GradeAssignmentDTO;
import ru.alvion.coursemodel.service.mapper.CourseAssignmentMapper;
import ru.alvion.coursemodel.service.mapper.GradeAssignmentMapper;
import ru.alvion.coursemodel.service.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GradeAssignmentService {

    private final Logger log = LoggerFactory.getLogger(GradeAssignmentService.class);

    private final GradeAssignmentRepository gradeAssignmentRepository;
    private final StudentRepository studentRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;

    private final GradeAssignmentMapper gradeAssignmentMapper;
    private final StudentMapper studentMapper;
    private final CourseAssignmentMapper courseAssignmentMapper;

    public GradeAssignmentService(GradeAssignmentRepository gradeAssignmentRepository, StudentRepository studentRepository, CourseAssignmentRepository courseAssignmentRepository, GradeAssignmentMapper gradeAssignmentMapper, StudentMapper studentMapper, CourseAssignmentMapper courseAssignmentMapper) {
        this.gradeAssignmentRepository = gradeAssignmentRepository;
        this.studentRepository = studentRepository;
        this.courseAssignmentRepository = courseAssignmentRepository;
        this.gradeAssignmentMapper = gradeAssignmentMapper;
        this.studentMapper = studentMapper;
        this.courseAssignmentMapper = courseAssignmentMapper;
    }

    public GradeAssignmentDTO save(GradeAssignmentDTO gradeAssignmentDTO) {
        log.debug("Request to save GradeAssignment : {}", gradeAssignmentDTO);
        GradeAssignment gradeAssignment = gradeAssignmentMapper.toEntity(gradeAssignmentDTO);
        gradeAssignment = gradeAssignmentRepository.save(gradeAssignment);
        CourseAssignment courseAssignment = courseAssignmentRepository.findById(gradeAssignment.getCourseAssignment().getId()).orElseThrow();
        Student student = courseAssignment.getStudent();
        reCalculateGpaByStudent(student);
        return gradeAssignmentMapper.toDto(gradeAssignment);
    }

    public GradeAssignmentDTO update(GradeAssignmentDTO gradeAssignmentDTO) {
        log.debug("Request to update GradeAssignment : {}", gradeAssignmentDTO);
        GradeAssignment gradeAssignment = gradeAssignmentMapper.toEntity(gradeAssignmentDTO);
        gradeAssignment = gradeAssignmentRepository.save(gradeAssignment);
        CourseAssignment courseAssignment = courseAssignmentRepository.findById(gradeAssignment.getCourseAssignment().getId()).orElseThrow();
        Student student = courseAssignment.getStudent();
        reCalculateGpaByStudent(student);
        return gradeAssignmentMapper.toDto(gradeAssignment);
    }

    public Optional<GradeAssignmentDTO> partialUpdate(GradeAssignmentDTO gradeAssignmentDTO) {
        log.debug("Request to partially update GradeAssignment : {}", gradeAssignmentDTO);

        return gradeAssignmentRepository
            .findById(gradeAssignmentDTO.getId())
            .map(existingGradeAssignment -> {
                gradeAssignmentMapper.partialUpdate(existingGradeAssignment, gradeAssignmentDTO);

                return existingGradeAssignment;
            })
            .map(gradeAssignment -> {
                gradeAssignmentRepository.save(gradeAssignment);
                CourseAssignment courseAssignment = courseAssignmentRepository.findById(gradeAssignment.getCourseAssignment().getId()).orElseThrow();
                Student student = courseAssignment.getStudent();
                reCalculateGpaByStudent(student);
                return gradeAssignment;
            })
            .map(gradeAssignmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<GradeAssignmentDTO> findAll() {
        log.debug("Request to get all GradeAssignments");
        return gradeAssignmentRepository
            .findAll()
            .stream()
            .map(gradeAssignmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<GradeAssignmentDTO> findOne(Long id) {
        log.debug("Request to get GradeAssignment : {}", id);
        return gradeAssignmentRepository.findById(id).map(gradeAssignmentMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete GradeAssignment : {}", id);
        gradeAssignmentRepository.deleteById(id);
    }

    public void reCalculateGpaByStudent(Student student) {
        List<CourseAssignment> courseAssignmentList = courseAssignmentRepository.findAllByStudent(student);
        if (courseAssignmentList.isEmpty())
            return;
        List<Float> averageGradesByCourse = new ArrayList<>();
        for (CourseAssignment courseAssignment : courseAssignmentList) {
            List<GradeAssignment> gradeAssignmentList =
                    gradeAssignmentRepository.findAllByCourseAssignment(courseAssignment);
            if (gradeAssignmentList.isEmpty())
                continue;
            averageGradesByCourse.add((float) gradeAssignmentList.stream().mapToDouble(GradeAssignment::getGrade).average().getAsDouble());
        }
        if (averageGradesByCourse.isEmpty())
            return;
        student.setGpa((float) averageGradesByCourse.stream().mapToDouble(a -> a).average().getAsDouble());
        studentRepository.save(student);
    }
}
