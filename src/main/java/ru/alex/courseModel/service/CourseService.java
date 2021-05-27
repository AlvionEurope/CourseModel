package ru.alex.courseModel.service;

import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.reposttory.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final ProfessorService professorService;

    public CourseService(CourseRepository courseRepository, ProfessorService professorService) {
        this.courseRepository = courseRepository;
        this.professorService = professorService;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.getOne(id);
    }

    public Course updateCourse(int id, Course course) {
        course.setId(id);
        return saveCourse(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public void addProfessorToCourse(int courseId, long professorId) {
        professorService.addCourse(courseId, professorId);
    }

    public void deleteProfessorFromCourse(int courseId, long professorId) {
        professorService.removeCourse(courseId, professorId);
    }

    public List<Professor> getCourseProfessors(int courseId) {
        return new ArrayList<>(courseRepository.getOne(courseId).getProfessors());
    }
}
