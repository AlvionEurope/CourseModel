package ru.wxw.coursemodel.wxw.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wxw.coursemodel.wxw.dto.StudentToCourseDTO;
import ru.wxw.coursemodel.wxw.dto.СompletedСoursesDTO;
import ru.wxw.coursemodel.wxw.entity.Course;
import ru.wxw.coursemodel.wxw.entity.CourseStudent;
import ru.wxw.coursemodel.wxw.entity.Student;
import ru.wxw.coursemodel.wxw.repository.CourseRepository;
import ru.wxw.coursemodel.wxw.repository.CourseStudentRepository;
import ru.wxw.coursemodel.wxw.repository.StudentRepository;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseStudentRepository courseStudentRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, CourseStudentRepository courseStudentRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.modelMapper = modelMapper;
    }


    public StudentToCourseDTO addStudent(int gradeBook, int numberCourse) {
        List<Course> courseList = courseRepository.findAll();
        List<Student> studentList = studentRepository.findAll();

        Course course = courseList.stream().filter(p -> p.getNumber()==numberCourse)
                .findFirst().orElse(null);
        Student student = studentList.stream().filter(p -> p.getGradeBook()==gradeBook)
                .findFirst().orElse(null);

        if (course != null && student != null) {
            CourseStudent courseStudent = courseStudentRepository.save(new CourseStudent(course, gradeBook, false));
            СompletedСoursesDTO comCorDTO = modelMapper.map(courseStudent.getCourse(), СompletedСoursesDTO.class);
            return new StudentToCourseDTO(comCorDTO, numberCourse);
        }
        return null;
    }
    public void deleteStudent(int gradeBook, int numberCourse) {
        List<Course> courseList = courseRepository.findAll();
        List<Student> studentList = studentRepository.findAll();

        Course course = courseList.stream().filter(p -> p.getNumber() == numberCourse)
                .findFirst().orElse(null);
        Student student = studentList.stream().filter(p -> p.getGradeBook() == gradeBook)
                .findFirst().orElse(null);

        if (course != null && student != null) {
            List<CourseStudent> courseStudentList = courseStudentRepository.findAll();

            CourseStudent courseStudent = courseStudentList.stream().filter(p -> p.getCourse().getId().equals(course.getId()))
                    .filter(p -> p.getStudentGradeBook() == gradeBook).findFirst().orElse(null);

            if (courseStudent != null) {
                courseStudentRepository.delete(courseStudent);
            }
        }
    }
}
