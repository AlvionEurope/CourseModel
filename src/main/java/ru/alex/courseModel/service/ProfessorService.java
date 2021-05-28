package ru.alex.courseModel.service;

import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public ProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public void addCourse(int courseId, long instructorId) {
        Professor professor = professorRepository.getOne(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.addCourse(course);
        course.addProfessor(professor);
        save(professor);
    }

    public void removeCourse(int courseId, long instructorId) {
        Professor professor = professorRepository.getOne(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.removeCourse(course);
        course.removeProfessor(professor);
        save(professor);
    }

    public Professor get(long id) {
        return professorRepository.getOne(id);
    }

    public Professor update(long id, Professor professor) {
        professor.setId(id);
        return save(professor);
    }

    public void deleteProfessor(long id) {
        professorRepository.deleteById(id);
    }
}
