package me.rudnikov.backend.service;

import me.rudnikov.backend.dto.create.CourseProgressCreateDto;
import me.rudnikov.backend.dto.read.CourseProgressDto;
import me.rudnikov.backend.dto.update.CourseProgressUpdateDto;

import java.util.List;

public interface CourseProgressService {
    Long createCourseProgress(CourseProgressCreateDto dto);
    CourseProgressDto readCourseProgressById(Long id);
    List<CourseProgressDto> readAllCourseProgresses();
    Boolean updateCourseProgressById(Long id, CourseProgressUpdateDto dto);
    Boolean deleteCourseProgressById(Long id);
}