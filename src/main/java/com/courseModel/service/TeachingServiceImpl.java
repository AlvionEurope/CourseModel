package com.courseModel.service;

import com.courseModel.dto.AverageScoreDTO;
import com.courseModel.dto.ScoreDTO;
import com.courseModel.dto.TeachingDTO;
import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import com.courseModel.enums.TeachingStatus;
import com.courseModel.exception.BadRequestException;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.TeachingMapper;
import com.courseModel.repository.TeachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class TeachingServiceImpl implements TeachingService {
    final TeachingRepository repository;
    final TeachingMapper mapper;

    @Override
    public Teaching create(Teaching teaching) {
        return repository.save(teaching);
    }

    @Override
    public boolean delete(int studentGradeBook, int courseNumber) {
        Optional<Teaching> teaching = getTeachingOptional(studentGradeBook, courseNumber);
        if (teaching.isEmpty()) {
            return false;
        }
        repository.delete(teaching.get());
        return true;
    }

    @Override
    public Teaching addScore(int courseNumber, int gradeBook, ScoreDTO score) {
        Teaching teaching = getTeachingOrNotFound(gradeBook, courseNumber);
        if (!teaching.getStatus().equals(TeachingStatus.IN_PROGRESS)) {
            throw new BadRequestException("Оценки можно ставить если обучение в статусе IN_PROGRESS, текущий статус обучения " + teaching.getStatus());
        }
        java.sql.Date date = new Date(System.currentTimeMillis());
        teaching.getScores().add(new TeachingToScore()
                .setTeachingId(teaching.getId())
                .setDate(date)
                .setScore(score.getScore()));
        return repository.save(teaching);
    }

    @Override
    public AverageScoreDTO getAvgScore(int courseNumber, int gradeBook) {
        Teaching teaching = getTeachingOrNotFound(gradeBook, courseNumber);
        if (!teaching.getStatus().equals(TeachingStatus.IN_PROGRESS)) {
            throw new BadRequestException("Обучение не в статусе IN_PROGRESS, текущий статус обучения " + teaching.getStatus());
        }
        OptionalDouble avrScore = teaching.getScores().stream()
                .mapToInt(TeachingToScore::getScore)
                .average();
        if (avrScore.isEmpty()) {
            throw new NotFoundException("Обучение не содержит оценок");
        }
        return new AverageScoreDTO((float) avrScore.getAsDouble());
    }

    @Override
    public TeachingDTO finishTeaching(int courseNumber, int gradeBook) {
        Teaching teaching = getTeachingOrNotFound(gradeBook, courseNumber);
        if (!teaching.getStatus().equals(TeachingStatus.IN_PROGRESS)) {
            throw new BadRequestException("Нельзя завершить обучение если оно не в статусе IN_PROGRESS, текущий статус обучения " + teaching.getStatus());
        }
        teaching.setStatus(TeachingStatus.FINISHED);
        OptionalDouble avrScore = teaching.getScores().stream()
                .mapToInt(TeachingToScore::getScore)
                .average();
        if (avrScore.isEmpty()) {
            throw new BadRequestException("Обучение не содержит оценок, для финальной оценки в обучении должна быть хотя бы одна оценка");
        }
        teaching.setFinalScore(Math.round((float) avrScore.getAsDouble()));
        return mapper.convert(repository.save(teaching));
    }

    @Override
    public List<Teaching> getFinishedTeachingByGradeBook(int gradeBook) {
        return repository.findFinishedTeachingWithCourseByStudentGradeBook(gradeBook);
    }

    @Override
    public TeachingDTO getTeachingDTO(int courseNumber, int gradeBook) {
        Teaching teaching = getTeachingOptional(gradeBook, courseNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдено обучение студента с зачетной книжкой %d на курсе номер %d", gradeBook, courseNumber)));
        return mapper.convert(teaching);
    }

    public Optional<Teaching> getTeachingOptional(int studentGradeBook, int courseNumber) {
        return repository.findByStudentGradeBookAndCourseNumber(studentGradeBook, courseNumber);
    }

    public Teaching getTeachingOrNotFound(int studentGradeBook, int courseNumber) {
        return getTeachingOptional(studentGradeBook, courseNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдено обучение студента с зачетной книжкой %d на курсе номер %d", studentGradeBook, courseNumber)));

    }
}
