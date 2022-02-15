package example.service;

import example.entity.Teacher;
import example.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(int id, Teacher teacher) {
        Teacher existing = teacherRepository.findById(id).orElseThrow();
        existing.setName(teacher.getName());
        existing.setAddress(teacher.getAddress());
        existing.setPhone(teacher.getPhone());
        existing.setPayment(teacher.getPayment());
        return existing;
    }

    @Override
    public Optional<Teacher> getTeacher(int id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> getTeacherByPhone(String phone) {
        return teacherRepository.findByPhone(phone);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }
}
