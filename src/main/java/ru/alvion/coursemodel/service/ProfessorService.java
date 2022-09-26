package ru.alvion.coursemodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.Professor;
import ru.alvion.coursemodel.repository.CourseAssignmentRepository;
import ru.alvion.coursemodel.repository.ProfessorRepository;
import ru.alvion.coursemodel.service.dto.ProfessorDTO;
import ru.alvion.coursemodel.service.mapper.ProfessorMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessorService {

    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    private final ProfessorRepository professorRepository;
    private final CourseAssignmentRepository courseAssignmentRepository;

    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, CourseAssignmentRepository courseAssignmentRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.courseAssignmentRepository = courseAssignmentRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        log.debug("Request to save Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    public ProfessorDTO update(ProfessorDTO professorDTO) {
        log.debug("Request to update Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    public Optional<ProfessorDTO> partialUpdate(ProfessorDTO professorDTO) {
        log.debug("Request to partially update Professor : {}", professorDTO);

        return professorRepository
                .findById(professorDTO.getId())
                .map(existingProfessor -> {
                    professorMapper.partialUpdate(existingProfessor, professorDTO);

                    return existingProfessor;
                })
                .map(professorRepository::save)
                .map(professorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ProfessorDTO> findAll() {
        log.debug("Request to get all Professors");
        return professorRepository.findAll().stream().map(professorMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<ProfessorDTO> findOne(Long id) {
        log.debug("Request to get Professor : {}", id);
        return professorRepository.findById(id).map(professorMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Professor : {}", id);
        professorRepository.deleteById(id);
    }

    @Transactional
    public Long countStudents(Professor professor) {
        Set<Course> courses = professor.getCourses();
        Long studentsCount = 0l;
        for (Course course : courses) {
            studentsCount += courseAssignmentRepository.countByCourse(course);
        }
        return studentsCount;
    }

    @Transactional
    public Double countAvgGrades(Professor professor) {
        Set<Course> courses = professor.getCourses();
        ArrayList<Integer> grades = new ArrayList<>();
        for (Course course : courses) {
            List<CourseAssignment> courseAssignments = courseAssignmentRepository.findAllByCourse(course);
            for (CourseAssignment courseAssignment : courseAssignments) {
                courseAssignment.getGradeAssignments().forEach(gradeAssignment -> {
                    grades.add(gradeAssignment.getGrade());
                });
            }
        }
        return grades.stream().mapToDouble(d -> d).average().orElse(.0);
    }
}
