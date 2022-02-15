package example.service;

import example.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course);

    Course updateCourse(int id, Course course);

    Optional<Course> getCourse(int id);

    Optional<Course> getCourseByNum(int num);

    List<Course> getCourses();

    void deleteCourse(int id);

    void addStudent(int courseId, int studentId);

    void removeStudent(int courseId, int studentId);

    void addTeacher(int courseId, int teacherId);

    void removeTeacher(int courseId, int teacherId);
}
