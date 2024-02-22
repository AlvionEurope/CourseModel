package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course postCourse(Course course) {
        return repository.save(course);
    }

    @Override
    public Course getCourse(Long courseId) {
        return repository.getReferenceById(courseId);
    }

    @Override
    public void deleteCourse(Long courseId) {
        repository.deleteById(courseId);
    }

    @Override
    public Course putCourse(Long courseId, Course course) throws Exception {
        if (repository.existsById(courseId)) {
            return repository.save(course);
        } else {
            throw new Exception("Not found Course");
        }
    }

    @Override
    public void addStudentOnCourse(Long courseId, Long studentId) {
        repository.addStudentCourse(studentId, courseId);
    }

    @Override
    public void deleteStudentCourse(Long courseId, Long studentId) {
        repository.deleteStudentCourse(studentId, courseId);
    }
}
