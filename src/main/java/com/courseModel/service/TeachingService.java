package com.courseModel.service;

import com.courseModel.entity.Teaching;

import java.util.Optional;

public interface TeachingService {
    Teaching create(Teaching teaching);

    boolean delete(int studentGradeBook, int courseNumber);

    Optional<Teaching> getTeaching(int studentGradeBook, int courseNumber);
}
