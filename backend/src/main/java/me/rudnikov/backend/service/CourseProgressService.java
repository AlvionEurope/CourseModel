package me.rudnikov.backend.service;

import me.rudnikov.backend.dto.create.CourseProgressCreateDto;
import me.rudnikov.backend.dto.read.CourseProgressDto;

import java.util.List;

public interface CourseProgressService {
    Long createCourseProgress(CourseProgressCreateDto dto);
    CourseProgressDto readCourseProgressById(Long id);
    List<CourseProgressDto> readAllCourseProgresses();
    Boolean updateCourseProgressById(Long id, CourseProgressDto dto);
    Boolean deleteCourseProgressById(Long id);
}