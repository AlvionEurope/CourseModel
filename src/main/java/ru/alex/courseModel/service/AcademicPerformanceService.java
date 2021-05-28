package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.AcademicPerformanceRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class AcademicPerformanceService {

    private final AcademicPerformanceRepository academicPerformanceRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public AcademicPerformanceService(AcademicPerformanceRepository academicPerformanceRepository,
                                      CourseRepository courseRepository,
                                      StudentRepository studentRepository,
                                      GradeRepository gradeRepository) {
        this.academicPerformanceRepository = academicPerformanceRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public AcademicPerformance get(AcademicPerformanceId id) {
        return academicPerformanceRepository.getOne(id);
    }

    public List<Course> getFinishedCourses(long studentId, boolean isFinished) {
        List<AcademicPerformance> studentCourses = academicPerformanceRepository.findAllByIsFinishedIsAndStudent_Id(isFinished, studentId);
        return studentCourses.stream().map(AcademicPerformance::getCourse).collect(Collectors.toList());
    }

    public List<Course> getAllStudentCourses(long studentId) {
        List<AcademicPerformance> studentCourses = academicPerformanceRepository.findAllByStudent_Id(studentId);
        return studentCourses.stream().map(AcademicPerformance::getCourse).collect(Collectors.toList());
    }

    public List<Student> getAllCourseStudents(int courseId) {
        List<AcademicPerformance> courseStudents = academicPerformanceRepository.findAllByCourse_Id(courseId);
        return courseStudents.stream().map(AcademicPerformance::getStudent).collect(Collectors.toList());
    }

    public void addStudentCourse(int courseId, long studentId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        new AcademicPerformance(student, course);
        studentRepository.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        academicPerformanceRepository.deleteById(id);
    }

    public AcademicPerformance finalizeStudentCourse(AcademicPerformanceId academicPerformanceId, Integer finalGrade) {
        AcademicPerformance academicPerformance = academicPerformanceRepository.getOne(academicPerformanceId);
        if (academicPerformance.getFinalGrade() == null && finalGrade != null) {
            academicPerformance.setFinalGrade(finalGrade);
            academicPerformance.setFinished(true);
        }
        academicPerformanceRepository.save(academicPerformance);
        return academicPerformance;
    }

    public float getCurrentAverageGrade(AcademicPerformanceId id) {
        return get(id).getAverageGrade();
    }

    public Integer getFinalGrade(AcademicPerformanceId id) {
        return academicPerformanceRepository.getOne(id).getFinalGrade();
    }

    public void addGrade(long studentId, int courseId, Grade grade) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        AcademicPerformance academicPerformance = academicPerformanceRepository.getOne(id);
        academicPerformance.addGrade(grade);
        academicPerformanceRepository.save(academicPerformance);
    }

    public Grade updateGradeById(Grade grade) {
        Grade newGrade = gradeRepository.getOne(grade.getId());
        newGrade.setValue(grade.getValue());
        return gradeRepository.save(newGrade);
    }

    public void deleteGradeById(long id) {
        gradeRepository.deleteById(id);
    }
}
