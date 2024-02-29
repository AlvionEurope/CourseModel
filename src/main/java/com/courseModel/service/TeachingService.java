package com.courseModel.service;

import com.courseModel.dto.AverageScoreDTO;
import com.courseModel.dto.ScoreDTO;
import com.courseModel.dto.TeachingDTO;
import com.courseModel.entity.Teaching;

import java.util.List;
import java.util.Optional;

public interface TeachingService {
    Teaching create(Teaching teaching);

    boolean delete(int studentGradeBook, int courseNumber);

    Optional<Teaching> getTeachingOptional(int studentGradeBook, int courseNumber);

    Teaching addScore(int courseNumber, int gradeBook, ScoreDTO score);

    AverageScoreDTO getAvgScore(int courseNumber, int gradeBook);

    TeachingDTO finishTeaching(int courseNumber, int gradeBook);

    TeachingDTO getTeachingDTO(int courseNumber, int gradeBook);

    Teaching getTeachingOrNotFound(int studentGradeBook, int courseNumber);

    List<Teaching> getFinishedTeachingByGradeBook(int gradeBook);
}
