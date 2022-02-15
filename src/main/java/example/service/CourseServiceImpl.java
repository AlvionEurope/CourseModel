package example.service;

import example.entity.Course;
import example.entity.Student;
import example.entity.Teacher;
import example.repo.CourseRepository;
import example.repo.StudentRepository;
import example.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(int id, Course course) {
        Course existing = courseRepository.findById(id).orElseThrow();
        existing.setCost(course.getCost());
        existing.setNum(course.getNum());
        existing.setTitle(course.getTitle());
        return existing;
    }

    @Override
    public Optional<Course> getCourse(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> getCourseByNum(int num) {
        return courseRepository.findByNum(num);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void addStudent(int courseId, int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        student.getCourses().add(course);
    }

    @Override
    public void removeStudent(int courseId, int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        student.getCourses().removeIf(c -> c.getId().equals(courseId));
    }

    @Override
    public void addTeacher(int courseId, int teacherId) {
        Teacher student = teacherRepository.findById(teacherId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        student.getCourses().add(course);
    }

    @Override
    public void removeTeacher(int courseId, int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        teacher.getCourses().removeIf(c -> c.getId().equals(courseId));
    }
}
