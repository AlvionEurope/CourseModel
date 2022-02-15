package example.service;

import example.entity.Student;
import example.repo.EnrollmentRepository;
import example.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        Student existing = studentRepository.findById(id).orElseThrow();
        existing.setName(student.getName());
        existing.setAddress(student.getAddress());
        existing.setPhone(student.getPhone());
        existing.setEmail(student.getEmail());
        existing.setBookId(student.getBookId());
        return existing;
    }

    @Override
    public Optional<Student> getStudent(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(this::checkAndUpdateAverageGrade);
        return student;
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        student.ifPresent(this::checkAndUpdateAverageGrade);
        return student;
    }

    @Override
    public List<Student> getStudents() {
        // probably should check average grade also here
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean canEnrollInCourse(int id, int courseId) {
        // assume student can enroll in course if it's not present
        // in its list of courses
        return getStudent(id)
                .map(s -> s.getCourses().stream()
                        .noneMatch(c -> c.getId().equals(courseId)))
                .orElse(false);
    }

    @Override
    public void updateAverageGrade(int id) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(this::updateAverageGrade);
    }

    private void checkAndUpdateAverageGrade(Student student) {
        if (student.getAverageGrade() == null) {
            updateAverageGrade(student);
        }
    }

    private void updateAverageGrade(Student student) {
        student.setAverageGrade(enrollmentRepository.getAverageGrade(student.getId()));
    }
}
