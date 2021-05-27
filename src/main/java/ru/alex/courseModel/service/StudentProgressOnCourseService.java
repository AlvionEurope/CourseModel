package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.StudentProgressOnCourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class StudentProgressOnCourseService {

    private final StudentProgressOnCourseRepository studentProgressOnCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public StudentProgressOnCourseService(StudentProgressOnCourseRepository studentProgressOnCourseRepository,
                                          CourseRepository courseRepository,
                                          StudentRepository studentRepository,
                                          GradeRepository gradeRepository) {
        this.studentProgressOnCourseRepository = studentProgressOnCourseRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public StudentProgressOnCourse getStudentCourse(StudentProgressOnCourseId id) {
        return studentProgressOnCourseRepository.getOne(id);
    }

    public List<Course> getFinishedCourses(long studentId, boolean isFinished) {
        List<StudentProgressOnCourse> studentCourses = studentProgressOnCourseRepository.findAllByIsFinishedIsAndStudent_Id(isFinished, studentId);
        return studentCourses.stream().map(StudentProgressOnCourse::getCourse).collect(Collectors.toList());

    }

    public List<Course> getAllStudentCourses(long studentId) {
        List<StudentProgressOnCourse> studentCourses = studentProgressOnCourseRepository.findAllByStudent_Id(studentId);
        return studentCourses.stream().map(StudentProgressOnCourse::getCourse).collect(Collectors.toList());
    }

    public List<Student> getAllCourseStudents(int courseId) {
        List<StudentProgressOnCourse> courseStudents = studentProgressOnCourseRepository.findAllByCourse_Id(courseId);
        return courseStudents.stream().map(StudentProgressOnCourse::getStudent).collect(Collectors.toList());
    }

    public void addStudentCourse(int courseId, long studentId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        new StudentProgressOnCourse(student, course);
        studentRepository.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId) {
        StudentProgressOnCourseId id = new StudentProgressOnCourseId(studentId, courseId);
        studentProgressOnCourseRepository.deleteById(id);
    }

    public void addGrade(long studentId, int courseId, Grade grade) {
        StudentProgressOnCourseId id = new StudentProgressOnCourseId(studentId, courseId);
        StudentProgressOnCourse studentProgressOnCourse = studentProgressOnCourseRepository.getOne(id);
        studentProgressOnCourse.addGrade(grade);
        studentProgressOnCourseRepository.save(studentProgressOnCourse);
    }

    public Grade updateGradeById(Grade grade) {
        Grade newGrade = gradeRepository.getOne(grade.getId());
        newGrade.setValue(grade.getValue());
        return gradeRepository.save(newGrade);
    }

    public void deleteGradeById(long id) {
        gradeRepository.deleteById(id);
    }

    public StudentProgressOnCourse finalizeStudentCourse(StudentProgressOnCourseId studentProgressOnCourseId, int finalGrade) {
        StudentProgressOnCourse studentProgressOnCourse = studentProgressOnCourseRepository.getOne(studentProgressOnCourseId);
        if (studentProgressOnCourse.getFinalGrade() == 0 && finalGrade !=0) {
            studentProgressOnCourse.setFinalGrade(finalGrade);
            studentProgressOnCourse.setFinished(true);
        }
        studentProgressOnCourseRepository.save(studentProgressOnCourse);
        return studentProgressOnCourse;
    }

    public float getCurrentAverageGrade(StudentProgressOnCourseId id) {
        return getStudentCourse(id).getAverageGrade();
    }

    public int getFinalGrade(StudentProgressOnCourseId id) {
        return studentProgressOnCourseRepository.getOne(id).getFinalGrade();
    }
}
