package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.reposttory.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private InstructorService instructorService;

    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAll(){
        return (List<Course>)courseRepo.findAll();
    }

    public Course getCourseById (int id){
        return courseRepo.findById(id).get();
    }

    public Course updateCourse (int id, Course course){
        course.setId(id);
        return saveCourse(course);
    }

    public void deleteCourse (int id){
        courseRepo.deleteById(id);
    }

    public void addStudentCourse(int courseId, long studentId){
        studentCourseService.addStudentCourse(courseId, studentId);
    }

    public void deleteStudentCourse(int courseId, long studentId){
        studentCourseService.removeStudentCourse(courseId, studentId);
    }

    public List<Student> getCourseStudents(int courseId) {
        return studentCourseService.getAllCourseStudents(courseId);
    }

    public void addInstructorToCourse (int courseId, long instructorId){
        instructorService.addCourse(courseId, instructorId);
    }

    public void deleteInstructorFromCourse (int courseId, long instructorId){
        instructorService.removeCourse(courseId, instructorId);
    }

    public List<Instructor> getCourseInstructors(int courseId) {
        return new ArrayList<>(courseRepo.findById(courseId).get().getInstructors());
    }
}
