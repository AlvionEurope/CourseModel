package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.model.Student;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    final StudentRepository repository;

    final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student postStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student getStudentId(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Student putStudent(Student student, Long id) throws Exception {
        if (repository.existsById(id)) {
            Student student1 = repository.getReferenceById(id);
            student1.setName(student.getName());
            student1.setEmail(student.getEmail());
            student1.setAddress(student.getAddress());
            student1.setTel(student.getTel());
            student1.setRecordBookNumber(student.getRecordBookNumber());
            student1.setAvgPoint(repository.getAvgPoint(id));
            return repository.save(student1);
        } else {
            throw new Exception("Not found Student");
        }
    }

    @Override
    public List<Course> canSignUpCourse(Long studentId) {
        List<Long> listTakenCourse = repository.getCoursesIdOfTaken(studentId);
        List<Long> listTakingCourse = repository.coursesId(studentId);
        Set<Long> coursesId = new HashSet<>();
        coursesId.addAll(listTakingCourse);
        coursesId.addAll(listTakenCourse);

        List<Course> courses = courseRepository.findAll();
        List<Course> takingCourseStudent = courseRepository.findAllById(coursesId);
        courses.removeAll(takingCourseStudent);

        return courses;
    }

    @Override
    public List<Course> getListCourseTaken(Long studentId) {
        List<Long> courses = repository.getCoursesIdOfTaken(studentId);

        return courseRepository.findAllById(courses);
    }

    @Override
    public Integer getAvgPointCourse(Long courseId, Long StudentId) {
        return repository.getAvgPointCourse(courseId, StudentId);
    }

    @Override
    public List<Integer> getPointCourse(Long courseId, Long StudentId) {
        return repository.getPointCourse(courseId, StudentId);
    }

    @Override
    public void addPointCourse(Long studentId, Long courseId, Integer point) {
        repository.addPointCourse(studentId, courseId, point);

        Student student = repository.getReferenceById(studentId);
        student.setAvgPoint(repository.getAvgPoint(student.getId()));
        repository.save(student);
    }

    @Override
    public Integer getFinalPointCourse(Long courseId, Long studentId) {
        Integer point = repository.getAvgPointCourse(courseId, studentId);
        repository.deleteFromCourse(studentId);
        repository.addListOfCoursesTaken(studentId, courseId);

        return point;
    }


}
