package com.courseModel.service;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.entity.Course;
import com.courseModel.entity.Student;
import com.courseModel.entity.Teaching;
import com.courseModel.enums.TeachingStatus;
import com.courseModel.exception.BadRequestException;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.CourseMapper;
import com.courseModel.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    final CourseRepository repository;
    final CourseMapper mapper;
    final StudentService studentService;
    final TeachingService teachingService;

    @Override
    public CourseDTO create(CreateCourseRequest courseRequest) {
        Course course = mapper.convert(courseRequest);
        return mapper.convert(repository.save(course));
    }


    @Override
    public CourseDTO readById(int courseNumber) {
        CourseDTO course = mapper.convert(getCourse(courseNumber));
        return course;
    }

    @Override
    public CourseDTO updateById(int courseNumber, CreateCourseRequest request) {
        Course course = getCourse(courseNumber);
        course.setCourseName(request.getCourseName())
                .setCost(request.getCost());
        return mapper.convert(repository.save(course));
    }

    @Override
    public boolean deleteById(int courseNumber) {
        if (repository.existsById(courseNumber)) {
            repository.deleteById(courseNumber);
            return true;
        }
        return false;
    }

    @Override
    public void addStudent(int courseNumber, int gradeBook) {
        studentService.readById(gradeBook);
        getCourse(courseNumber);
        if (teachingService.getTeaching(gradeBook, courseNumber).isPresent()) {
            throw new BadRequestException(
                    String.format("Студент с зачетной книжкой %d уже записан на курс с номером %d", gradeBook, courseNumber));
        }
        teachingService.create(new Teaching()
                .setCourseNumber(courseNumber)
                .setStudentGradeBook(gradeBook)
                .setStatus(TeachingStatus.NOT_STARTED));
    }

    @Override
    public void deleteStudent(int courseNumber, int gradeBook) {

    }

    private Course getCourse(int courseNumber) {
        return repository.findById(courseNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Курс с номером %d не найден", courseNumber)));
    }
}
