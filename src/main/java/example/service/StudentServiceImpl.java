package example.service;

import example.entity.Student;
import example.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

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
        existing.setAverageGrade(student.getAverageGrade());
        return existing;
    }

    @Override
    public Optional<Student> getStudent(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> getStudents() {
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
}
