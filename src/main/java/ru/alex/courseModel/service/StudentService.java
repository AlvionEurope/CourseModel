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
    private CourseRepo courseRepo;

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

    public List<Course> getCurrentCourses(Student student){
        return studentCourseService.getCurrentCourses(student);
    }

    public List<Course> getFinishedCourses (Student student){
        return studentCourseService.getFinishedCourses(student);
    }

    public void addStudentCourse(Student student, Course course){
        studentCourseService.addStudentCourse(student, course);
//        studentRepo.save(student);
//        courseRepo.save(course);
    }

    public void deleteStudentCourse(Student student, Course course){
        studentCourseService.removeStudentCourse(student, course);
    }







    public void addStudentCourseGrade(StudentCourse studentCourse, Grade grade){
        studentCourse.getGrades().add(grade);
    }

    public void updateStudentCourseGrade(StudentCourse studentCourse, int id, Grade grade){
        studentCourse.getGrades().set(id, grade);
    }

    public void deleteStudentCourseGrade(StudentCourse studentCourse, int id){
        studentCourse.getGrades().remove(id);
    }





//    public List<Course> getAvailableCourses(long studentId){
//        return getStudentCourses(studentId);
//    }
//

}
