package example.service;

import example.entity.Course;
import example.entity.EnrollmentGrade;
import example.entity.Student;
import example.repo.CourseRepository;
import example.repo.EnrollmentRepository;
import example.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
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
        //TODO how final grade should be calculated?
        return enrollmentRepository.getAverageGrade(studentId, courseId);
    }

    @Override
    public EnrollmentGrade addGrade(int studentId, int courseId, float grade) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        return enrollmentRepository.save(new EnrollmentGrade(null, grade, student, course));
    }

    @Override
    public EnrollmentGrade updateGrade(long gradeId, float grade) {
        EnrollmentGrade enrollmentGrade = enrollmentRepository.findById(gradeId).orElseThrow();
        enrollmentGrade.setGrade(grade);
        return enrollmentGrade;
    }

    @Override
    public void removeGrade(long gradeId) {
        enrollmentRepository.deleteById(gradeId);
    }
}
