package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.StudentProgressOnCourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class StudentProgressOnCourseService {

    @Autowired
    private StudentProgressOnCourseRepository studentProgressOnCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;

    private List<Course> getCourses(long studentId, boolean finished) {
        List<Course> currentCourses = new ArrayList<>();
        for (StudentProgressOnCourse studentProgressOnCourse : studentProgressOnCourseRepository.findAll()) {
            if (studentProgressOnCourse.getStudent().getId() == studentId && studentProgressOnCourse.isFinished() == finished) {
                currentCourses.add(studentProgressOnCourse.getCourse());
            }
        }
        return currentCourses;
    }

    public List<Course> getAvailableCourse(long studentId) {
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            if (!isStudentPresent(studentId)) {
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> getAllStudentCourses(long studentId) {
        List<Course> studentCourses = new ArrayList<>();
        studentCourses.addAll(getFinishedCourses(studentId));
        studentCourses.addAll(getCurrentCourses(studentId));
        return studentCourses;
    }

    public StudentProgressOnCourse getStudentCourse(StudentProgressOnCourseId id) {
        return studentProgressOnCourseRepository.getOne(id);
    }

    public List<Course> getCurrentCourses(long studentId) {
        return getCourses(studentId, false);
    }

    public List<Course> getFinishedCourses(long studentId) {
        return getCourses(studentId, true);
    }

    public void addStudentCourse(int courseId, long studentId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        new StudentProgressOnCourse(student, course);
        studentRepository.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        StudentProgressOnCourseId id = new StudentProgressOnCourseId(studentId, courseId);
        studentProgressOnCourseRepository.getOne(id).removeCourse(student, course);
        studentProgressOnCourseRepository.deleteById(id);
    }

    public List<Student> getAllCourseStudents(int courseId) {
        List<Student> courseStudents = new ArrayList<>();
        for (StudentProgressOnCourse studentProgressOnCourse : studentProgressOnCourseRepository.findAll()) {
            if (studentProgressOnCourse.getCourse().getId() == courseId) {
                courseStudents.add(studentProgressOnCourse.getStudent());
            }
        }
        return courseStudents;
    }

    public void addGrade(StudentProgressOnCourseId id, Grade grade) {
        StudentProgressOnCourse studentProgressOnCourse = studentProgressOnCourseRepository.getOne(id);
        studentProgressOnCourse.addGrade(grade);
        studentProgressOnCourseRepository.save(studentProgressOnCourse);
    }

    public Grade updateGradeById(long id, Grade grade) {
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public void deleteGradeById(long id) {
        gradeRepository.deleteById(id);
    }

    public StudentProgressOnCourse setFinalStudentCourse(StudentProgressOnCourse studentProgressOnCourse, int finalGrade) {
        if (studentProgressOnCourse.getFinalGrade() == 0 && finalGrade !=0) {
            studentProgressOnCourse.setFinalGrade(finalGrade);
            studentProgressOnCourse.setFinished(true);
        }
        return studentProgressOnCourse;
    }

    public int getFinalGrade(StudentProgressOnCourse studentProgressOnCourse) {
        return studentProgressOnCourse.getFinalGrade();
    }

    public float getCurrentAverageGrade(StudentProgressOnCourse studentProgressOnCourse) {
        return studentProgressOnCourse.getAverageGrade();
    }

    public boolean isStudentPresent(long studentId) {
        for (StudentProgressOnCourse studentProgressOnCourse : studentProgressOnCourseRepository.findAll()) {
            if (studentProgressOnCourse.getStudent().getId() == studentId) {
                return true;
            }
        }
        return false;
    }
}
