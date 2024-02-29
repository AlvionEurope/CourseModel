package com.courseModel.service;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.entity.Course;
import com.courseModel.entity.Teaching;
import com.courseModel.enums.TeachingStatus;
import com.courseModel.exception.BadRequestException;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.CourseMapper;
import com.courseModel.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    final CourseRepository repository;
    final CourseMapper mapper;
    final StudentService studentService;
    final TeachingService teachingService;
    final ProfessorService professorService;

    @Override
    public CourseDTO create(CreateCourseRequest courseRequest) {
        validateProfessorId(courseRequest.getProfessorId());
        Course course = mapper.convert(courseRequest);
        return mapper.convert(repository.save(course));
    }

    @Override
    public CourseDTO readByCourseNumber(int courseNumber) {
        return mapper.convert(getCourse(courseNumber));
    }

    @Override
    public CourseDTO updateByCourseNumber(int courseNumber, CreateCourseRequest request) {
        validateProfessorId(request.getProfessorId());
        Course course = getCourse(courseNumber);
        course.setName(request.getName())
                .setCost(request.getCost())
                .setProfessorId(request.getProfessorId());
        return mapper.convert(repository.save(course));
    }

    @Override
    public boolean deleteByCourseNumber(int courseNumber) {
        if (repository.existsById(courseNumber)) {
            repository.deleteById(courseNumber);
            return true;
        }
        return false;
    }

    @Override
    public void addStudent(int courseNumber, int gradeBook) {
        studentService.readByGradeBook(gradeBook);
        Course course = getCourse(courseNumber);
        if (teachingService.getTeachingOptional(gradeBook, courseNumber).isPresent()) {
            throw new BadRequestException(
                    String.format("Студент с зачетной книжкой %d уже записан на курс с номером %d", gradeBook, courseNumber));
        }
        teachingService.create(new Teaching()
                .setCourse(course)
                .setStudentGradeBook(gradeBook)
                .setStatus(TeachingStatus.NOT_STARTED));
    }

    @Override
    public void deleteStudent(int courseNumber, int gradeBook) {
        if (teachingService.getTeachingOptional(gradeBook, courseNumber).isPresent()) {
            teachingService.delete(gradeBook, courseNumber);
        }
    }

    private Course getCourse(int courseNumber) {
        return repository.findById(courseNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Курс с номером %d не найден", courseNumber)));
    }

    private void validateProfessorId(Integer professorId) {
        if (professorId == null) {
            return;
        }
        try {
            professorService.getProfessor(professorId);
        } catch ( NotFoundException e ) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
