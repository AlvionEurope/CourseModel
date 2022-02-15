package example.service;

import example.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);

    Student updateStudent(int id, Student student);

    Optional<Student> getStudent(Integer id);

    Optional<Student> getStudentByEmail(String email);

    List<Student> getStudents();

    void deleteStudent(int id);

    boolean canEnrollInCourse(int id, int courseId);

    void updateAverageGrade(int id);
}
