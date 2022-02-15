package example.service;

import example.entity.Course;
import example.entity.EnrollmentGrade;
import example.entity.Student;
import example.repo.CourseRepository;
import example.repo.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseRepository courseRepository;

    @Override
    public List<EnrollmentGrade> getGrades() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<EnrollmentGrade> getGrades(int studentId) {
        return enrollmentRepository.findStudentGrades(studentId);
    }

    @Override
    public List<EnrollmentGrade> getGrades(int studentId, int courseId) {
        return enrollmentRepository.findStudentGrades(studentId, courseId);
    }

    @Override
    public float getAverageGrade(int studentId) {
        return enrollmentRepository.getAverageGrade(studentId);
    }

    @Override
    public float getAverageGrade(int studentId, int courseId) {
        return enrollmentRepository.getAverageGrade(studentId, courseId);
    }

    @Override
    public float getFinalGrade(int studentId, int courseId) {
        // final grade is just a rounded average grade
        return Math.round(enrollmentRepository.getAverageGrade(studentId, courseId));
    }

    @Override
    public EnrollmentGrade addGrade(int studentId, int courseId, float grade) {
        Student student = studentService.getStudent(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        EnrollmentGrade enrollmentGrade = enrollmentRepository.save(new EnrollmentGrade(null, grade, student, course));
        studentService.updateAverageGrade(studentId);
        return enrollmentGrade;
    }

    @Override
    public EnrollmentGrade updateGrade(long gradeId, float grade) {
        EnrollmentGrade enrollmentGrade = enrollmentRepository.findById(gradeId).orElseThrow();
        enrollmentGrade.setGrade(grade);
        enrollmentRepository.save(enrollmentGrade);
        studentService.updateAverageGrade(enrollmentGrade.getStudent().getId());
        return enrollmentGrade;
    }

    @Override
    public void removeGrade(long gradeId) {
        EnrollmentGrade enrollmentGrade = enrollmentRepository.findById(gradeId).orElseThrow();
        Integer student_id = enrollmentGrade.getStudent().getId();
        enrollmentRepository.deleteById(gradeId);
        studentService.updateAverageGrade(student_id);
    }
}
