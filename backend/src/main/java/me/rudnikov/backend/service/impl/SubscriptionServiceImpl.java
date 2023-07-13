package me.rudnikov.backend.service.impl;

import me.rudnikov.backend.entity.Course;
import me.rudnikov.backend.entity.CourseProgress;
import me.rudnikov.backend.entity.Student;
import me.rudnikov.backend.exception.ResourceNotFoundException;
import me.rudnikov.backend.repository.CourseProgressRepository;
import me.rudnikov.backend.repository.CourseRepository;
import me.rudnikov.backend.repository.StudentRepository;
import me.rudnikov.backend.service.SubscriptionService;

import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseProgressRepository courseProgressRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean subscribeStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User with that id not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        CourseProgress courseProgress = new CourseProgress();

        courseProgress.setStudent(student);
        courseProgress.setCourse(course);

        course.getStudents().add(student);
        student.getCourseProgressList().add(courseProgress);

        studentRepository.save(student);
        courseRepository.save(course);
        courseProgressRepository.save(courseProgress);

        return true;
    }

    @Override
    public Boolean unsubscribeStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User with that id not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with that id not found"));

        student.getCourseProgressList()
                .stream()
                .filter(courseProgress -> courseProgress.getCourse().getName().equals(course.getName())
                ).findFirst().ifPresent(courseProgressRepository::delete);

        return true;
    }

}