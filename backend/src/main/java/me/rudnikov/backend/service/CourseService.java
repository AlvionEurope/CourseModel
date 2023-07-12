package me.rudnikov.backend.service;

import me.rudnikov.backend.dto.create.CourseCreateDto;
import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.entity.Professor;

import java.util.List;

public interface CourseService {

    Long createCourse(CourseCreateDto dto);
    CourseDto readCourseById(Long id);
    List<CourseDto> readAllCourses();
    List<CourseDto> readAllCoursesByProfessorId(Long id);
    Boolean updateCourseById(Long id, CourseDto dto);
    Boolean deleteCourseById(Long id);

}