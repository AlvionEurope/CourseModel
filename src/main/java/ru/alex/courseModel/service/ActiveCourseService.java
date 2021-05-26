package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.GradeRepo;
import ru.alex.courseModel.reposttory.ActiveCourseRepo;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ActiveCourseService {

    @Autowired
    private ActiveCourseRepo activeCourseRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GradeRepo gradeRepo;

    private List<Course> getCourses(long studentId, boolean finished){
        List<Course> currentCourses = new ArrayList<>();
        for (ActiveCourse activeCourse : activeCourseRepo.findAll()){
            if(activeCourse.getStudent().getId() == studentId && activeCourse.isFinished() == finished){
                currentCourses.add(activeCourse.getCourse());
            }
        }
        return currentCourses;
    }

    public List<Course> getAvailableCourse (long studentId){
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepo.findAll()){
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

    public ActiveCourse getStudentCourse(StudentCourseId id){
        return activeCourseRepo.getOne(id);
    }

    public List<Course> getCurrentCourses(long studentId) {
        return getCourses(studentId, false);
    }

    public List<Course> getFinishedCourses(long studentId){
        return getCourses(studentId, true);
    }

    public void addStudentCourse(int courseId, long studentId){
        Student student = studentRepo.getOne(studentId);
        Course course = courseRepo.getOne(courseId);
        new ActiveCourse(student, course);
        studentRepo.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId){
        Student student = studentRepo.getOne(studentId);
        Course course = courseRepo.getOne(courseId);
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        activeCourseRepo.getOne(id).removeCourse(student, course);
        activeCourseRepo.deleteById(id);
    }

    public List<Student> getAllCourseStudents (int courseId){
        List<Student> courseStudents = new ArrayList<>();
        for (ActiveCourse activeCourse : activeCourseRepo.findAll()){
            if (activeCourse.getCourse().getId() == courseId){
                courseStudents.add(activeCourse.getStudent());
            }
        }
        return courseStudents;
    }

    public void addGrade(StudentCourseId id, Grade grade){
        ActiveCourse activeCourse = activeCourseRepo.getOne(id);
        activeCourse.addGrade(grade);
        activeCourseRepo.save(activeCourse);
    }

    public Grade updateGradeById(long id, Grade grade){
        grade.setId(id);
        return gradeRepo.save(grade);
    }

    public void deleteGradeById(long id){
        gradeRepo.deleteById(id);
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
