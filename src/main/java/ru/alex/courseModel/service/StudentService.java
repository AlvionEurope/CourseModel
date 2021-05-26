package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.StudentCourseRepo;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentCourseService studentCourseService;

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return (List<Student>)studentRepo.findAll();
    }

    public Student getStudentById(long id){
        return studentRepo.findById(id).get();
    }

    public Student updateStudent(long id, Student student){
        student.setId(id);
        return saveStudent(student);
    }

    public void deleteStudent (long id){
        studentRepo.deleteById(id);
    }

    public StudentCourse getStudentCourse(StudentCourseId id){
        return studentCourseService.getStudentCourse(id);
    }

    public List<Course> getAllStudentCourses(long studentId){
        return studentCourseService.getAllStudentCourses(studentId);
    }

    public List<Course> getCurrentCourses(long studentId){
        return studentCourseService.getCurrentCourses(studentId);
    }

    public List<Course> getFinishedCourses (long studentId){
        return studentCourseService.getFinishedCourses(studentId);
    }

    public void addStudentCourse(long studentId, int courseId){
        studentCourseService.addStudentCourse(studentId, courseId);
    }

    public void deleteStudentCourse(long studentId, int courseId){
        studentCourseService.removeStudentCourse(studentId, courseId);
    }

    public List<Course> getAvailableCourses(long studentId){
        return studentCourseService.getAvailableCourse(studentId);
    }




    /*-------------------------------------------------------*/

    public void addGrade(StudentCourseId id, int value){
        studentCourseService.addGrade(id, value);
    }


    public void addStudentCourseGrade(StudentCourse studentCourse, Grade grade){
        studentCourse.getGrades().add(grade);
    }

    public Grade updateStudentCourseGrade(long id, int value){
        return studentCourseService.updateGradeById(id, value);
    }

    public void deleteGrade(long id){
        studentCourseService.deleteGradeById(id);
    }



}
