package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.reposttory.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ActiveCourseService activeCourseService;

    @Autowired
    private ProfessorService professorService;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAll(){
        return (List<Course>) courseRepository.findAll();
    }

    public Course getCourseById (int id){
        return courseRepository.getOne(id);
    }

    public Course updateCourse (int id, Course course){
        course.setId(id);
        return saveCourse(course);
    }

    public void deleteCourse (int id){
        courseRepository.deleteById(id);
    }

    public void addStudentCourse(int courseId, long studentId){
        activeCourseService.addStudentCourse(courseId, studentId);
    }

    public void deleteStudentCourse(int courseId, long studentId){
        activeCourseService.removeStudentCourse(courseId, studentId);
    }

    public List<Student> getCourseStudents(int courseId) {
        return activeCourseService.getAllCourseStudents(courseId);
    }

    public void addProfessorToCourse(int courseId, long instructorId){
        professorService.addCourse(courseId, instructorId);
    }

    public void deleteProfessorFromCourse(int courseId, long instructorId){
        professorService.removeCourse(courseId, instructorId);
    }

    public List<Professor> getCourseProfessors(int courseId) {
        return new ArrayList<>(courseRepository.getOne(courseId).getProfessors());
    }
}
