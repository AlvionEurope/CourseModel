package example.service;

import example.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);

    Teacher updateTeacher(int id, Teacher teacher);

    Optional<Teacher> getTeacher(int id);

    Optional<Teacher> getTeacherByPhone(String phone);

    List<Teacher> getTeachers();

    void deleteTeacher(int id);
}
