package com.courseModel.service;

import com.courseModel.dto.AverageScoreDTO;
import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Course;
import com.courseModel.entity.Student;
import com.courseModel.entity.Teaching;
import com.courseModel.enums.TeachingStatus;
import com.courseModel.exception.BadRequestException;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.StudentMapper;
import com.courseModel.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;
    final StudentMapper mapper;
    final TeachingService teachingService;

    @Override
    public StudentDTO create(CreateStudentRequest request) {
        Student student = mapper.convert(request);
        return mapper.convert(studentRepository.save(student));
    }


    @Override
    public StudentDTO readByGradeBook(int gradeBook) {
        return mapper.convert(getStudent(gradeBook));
    }

    @Override
    public StudentDTO updateByGradeBook(int gradeBook, CreateStudentRequest request) {
        Student student = getStudent(gradeBook);
        student.setName(request.getName())
                .setAddress(request.getAddress())
                .setPhone(request.getPhone())
                .setEmail(request.getEmail());
        return mapper.convert(studentRepository.save(student));
    }

    @Override
    public boolean deleteByGradeBook(int gradeBook) {
        if (studentRepository.existsById(gradeBook)) {
            studentRepository.deleteById(gradeBook);
            return true;
        }
        return false;
    }

    @Override
    public void signUpCourse(int courseNumber, int gradeBook) {
        Teaching teaching = teachingService.getTeachingOrNotFound(gradeBook, courseNumber);
        if (!teaching.getStatus().equals(TeachingStatus.NOT_STARTED)) {
            throw new BadRequestException("Начать обучение можно если обучение в статусе NOT_STARTED текущий статус обучения " + teaching.getStatus());
        }
        teaching.setStatus(TeachingStatus.IN_PROGRESS);
        teachingService.create(teaching);
    }

    @Override
    public AverageScoreDTO getAvgGrade(int gradeBook) {
        OptionalDouble avrScore = teachingService.getFinishedTeachingByGradeBook(gradeBook).stream()
                .mapToInt(Teaching::getFinalScore)
                .average();
        if (avrScore.isEmpty()) {
            throw new NotFoundException("Нет завершенных курсов");
        }
        return new AverageScoreDTO((float) avrScore.getAsDouble());
    }


    @Override
    public List<Course> finishedCoursesByGradeBook(int gradeBook) {
        getStudent(gradeBook);
        return teachingService.getFinishedTeachingByGradeBook(gradeBook)
                .stream()
                .map(Teaching::getCourse)
                .collect(Collectors.toList());
    }

    private Student getStudent(int gradeBook) {
        return studentRepository.findById(gradeBook)
                .orElseThrow(() -> new NotFoundException(String.format("Студент с зачетной книжкой номер %d не найден", gradeBook)));
    }
}
