package ru.alex.courseModel.service;

import ru.alex.courseModel.model.Course;

import java.util.Set;

public interface CourseServise {
    void addCourse(Course course);
    Course getById(long id);
    Set<Course> getAll();
}
