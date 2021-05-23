package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.model.InstructorDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

//    public StudentDto saveStudent(Student student) {
//        return StudentDto.toDto(studentRepo.save(student));
//    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return (List<Student>)studentRepo.findAll();
    }

    public Student getStudentById(long id){
        return studentRepo.findById(id).get();
    }




//    public Student addCourse(Long courseId, Long studentId) {
//        Student student = studentRepo.findById(studentId).get();
//        student.addCourse(courseRepo.findById(courseId).get());
//        return saveStudent(student);
//    }

//    public StudentDto getOne(long id){
//        return StudentDto.toDto(studentRepo.findById(id).get());
//    }

    public Student update(long id, Student student){
        student.setId(id);
        return saveStudent(student);
    }

//    public StudentCourse getStudentCourseById(Course course){
//
//    }

//    public long delete(long id){
//        if(studentRepo.existsById(id)) {
//            studentRepo.deleteById(id);
//        }
//        return id;
//    }

//    public List<Course> getCurrentCourses(long studentId){
//        return getStudentCourses(studentId, false);
//    }
//
//    public List<Course> getCompletedCourses(long studentId){
//        return getStudentCourses(studentId, true);
//    }
//
//    public List<Course> getAvailableCourses(long studentId){
//        return getStudentCourses(studentId);
//    }
//
//    private List<Course> getStudentCourses(long studentId, boolean isFinished) {
//        Student student = getOne(studentId);
//        return student.getStudentCourses()
//                .stream()
//                .filter(entity -> entity.isFinished() && isFinished)
//                .map(StudentCourse::getCourse)
//                .collect(Collectors.toList());
//    }
//
//    private List<Course> getStudentCourses(long studentId) {
//        Student student = getOne(studentId);
//        return student.getStudentCourses()
//                .stream()
//                .map(StudentCourse::getCourse)
//                .collect(Collectors.toList());
//    }
//

}
