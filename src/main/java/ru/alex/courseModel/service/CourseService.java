package ru.alex.courseModel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.entity.TrainingCourse;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public void save(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course get(int id) {
        return courseRepository.getOne(id);
    }

    public void update(int id, Course course) {
        course.setId(id);
        save(course);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    public List<Professor> getCourseProfessors(int courseId) {
        return new ArrayList<>(courseRepository.getOne(courseId).getProfessors());
    }

    public void addStudentToCourse(int courseId, long studentId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        TrainingCourse trainingCourse = new TrainingCourse(student, course);
        trainingCourse.addRelations();
        courseRepository.save(course);
    }

    public List<Course> getAvailableStudentCourses(long studentId) {
        // Не придумал, как одним запросом сделать
        List<Course> availableCourses = new ArrayList<>(courseRepository.findAll());
        availableCourses.removeAll(courseRepository.findAllByTrainingCourses_Student_Id(studentId));
        return availableCourses;
    }
}
