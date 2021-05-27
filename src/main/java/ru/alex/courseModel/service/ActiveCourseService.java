package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.ActiveCourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ActiveCourseService {

    @Autowired
    private ActiveCourseRepository activeCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;

    private List<Course> getCourses(long studentId, boolean finished){
        List<Course> currentCourses = new ArrayList<>();
        for (ActiveCourse activeCourse : activeCourseRepository.findAll()){
            if(activeCourse.getStudent().getId() == studentId && activeCourse.isFinished() == finished){
                currentCourses.add(activeCourse.getCourse());
            }
        }
        return currentCourses;
    }

    public List<Course> getAvailableCourse (long studentId){
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepository.findAll()){
            if (!course.isPresent(studentId)){
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> getAllStudentCourses (long studentId){
        List<Course> studentCourses = new ArrayList<>();
        studentCourses.addAll(getFinishedCourses(studentId));
        studentCourses.addAll(getCurrentCourses(studentId));
        return studentCourses;
    }

    public ActiveCourse getStudentCourse(ActiveCourseId id){
        return activeCourseRepository.getOne(id);
    }

    public List<Course> getCurrentCourses(long studentId) {
        return getCourses(studentId, false);
    }

    public List<Course> getFinishedCourses(long studentId){
        return getCourses(studentId, true);
    }

    public void addStudentCourse(int courseId, long studentId){
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        new ActiveCourse(student, course);
        studentRepository.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId){
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        ActiveCourseId id = new ActiveCourseId(studentId, courseId);
        activeCourseRepository.getOne(id).removeCourse(student, course);
        activeCourseRepository.deleteById(id);
    }

    public List<Student> getAllCourseStudents (int courseId){
        List<Student> courseStudents = new ArrayList<>();
        for (ActiveCourse activeCourse : activeCourseRepository.findAll()){
            if (activeCourse.getCourse().getId() == courseId){
                courseStudents.add(activeCourse.getStudent());
            }
        }
        return courseStudents;
    }

    public void addGrade(ActiveCourseId id, Grade grade){
        ActiveCourse activeCourse = activeCourseRepository.getOne(id);
        activeCourse.addGrade(grade);
        activeCourseRepository.save(activeCourse);
    }

    public Grade updateGradeById(long id, Grade grade){
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public void deleteGradeById(long id){
        gradeRepository.deleteById(id);
    }

    public ActiveCourse setFinalStudentCourse (ActiveCourse activeCourse, int finalGrade){
        if (activeCourse.getFinalGrade() == 0 && finalGrade !=0) {
            activeCourse.setFinalGrade(finalGrade);
            activeCourse.setFinished(true);
        }
        return activeCourse;
    }

    public int getFinalGrade (ActiveCourse activeCourse){
        return activeCourse.getFinalGrade();
    }

    public float getCurrentAverageGrade (ActiveCourse activeCourse){
        return activeCourse.getAverageGrade();
    }
}
