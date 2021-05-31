package ru.alex.courseModel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.ProfessorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public Professor get(long id) {
        return professorRepository.getOne(id);
    }

    public void update(long id, Professor professor) {
        professor.setId(id);
        save(professor);
    }

    public void deleteProfessor(long id) {
        professorRepository.deleteById(id);
    }

    public void assignCourse(int courseId, long instructorId) {
        Professor professor = get(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.getCourses().add(course);
        course.getProfessors().add(professor);
        save(professor);
    }

    public void removeCourse(int courseId, long instructorId) {
        Professor professor = get(instructorId);
        Course course = courseRepository.getOne(courseId);
        professor.getCourses().remove(course);
        course.getProfessors().remove(professor);
        save(professor);
    }
}
