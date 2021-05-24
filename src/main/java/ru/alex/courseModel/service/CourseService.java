package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.reposttory.CourseRepo;

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

    public Course getCourseById (long id){
        return courseRepo.findById(id).get();
    }

    public Course updateCourse (long id, Course course){
        course.setId(id);
        return saveCourse(course);
    }

    public void deleteCourse (long id){
        courseRepo.deleteById(id);
    }

    public void addStudent (Student student, Course course){
        studentCourseService.addStudentCourse(student, course);
    }

    public void deleteStudent(Student student, Course course){
        studentCourseService.removeStudentCourse(student, course);
    }

    public void addInstructorToCourse (Course course, Instructor instructor){
        instructorService.addCourse(course.getId(), instructor.getId());
    }

    public void deleteInstructor(Course course, Instructor instructor){
        instructorService.removeCourse(course.getId(), instructor.getId());
    }


}
