package ru.wxw.coursemodel.wxw.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wxw.coursemodel.wxw.dto.StudentDTO;
import ru.wxw.coursemodel.wxw.dto.StudentToCourseDTO;
import ru.wxw.coursemodel.wxw.dto.СompletedСoursesDTO;
import ru.wxw.coursemodel.wxw.entity.Course;
import ru.wxw.coursemodel.wxw.entity.CourseStudent;
import ru.wxw.coursemodel.wxw.entity.Student;
import ru.wxw.coursemodel.wxw.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private final CourseStudentService courseStudentService;
    @Autowired
    private final ModelMapper modelMapper;


    public StudentService(StudentRepository studentRepository, CourseService courseService, CourseStudentService courseStudentService, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.courseStudentService = courseStudentService;
        this.modelMapper = modelMapper;
    }
    public StudentDTO save(StudentDTO studentDTO){
        Student student = studentRepository.save(modelMapper.map(studentDTO, Student.class));
        return modelMapper.map(student, StudentDTO.class);
    }
    public void delete(Long id) { studentRepository.deleteById(id);}
    @Transactional(readOnly = true)
    public List<StudentDTO> getAll() {
       return studentRepository.findAll().stream().map(p -> modelMapper.map(p, StudentDTO.class)).collect(Collectors.toCollection(ArrayList::new));
    }
    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            return modelMapper.map(student, StudentDTO.class);
        }
        return null;
    }

    public StudentToCourseDTO addStudentToCourse(int gradeBook, int numberCourse) {
        return courseService.addStudent(gradeBook,numberCourse);
    }
    @Transactional(readOnly = true)
    public List<СompletedСoursesDTO> getFinishedCourse(int gradeBook) {
        List<Course> courseList = new ArrayList<>();
        List<CourseStudent> studentList = courseStudentService.findAll();
        studentList = studentList.stream().filter(p-> p.getStudentGradeBook()==gradeBook && p.isFinished())
                .collect(Collectors.toList());
        for(CourseStudent courseStudent : studentList) {
            courseList.add(courseStudent.getCourse());
        }
        return courseList.stream().map(p -> modelMapper.map(p, СompletedСoursesDTO.class)).collect(Collectors.toCollection(ArrayList::new));
    }
    public StudentDTO update(StudentDTO studentDTO, Long id) {
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setId(id);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }
}
