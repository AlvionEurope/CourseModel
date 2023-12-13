package com.courseModel.service;

import com.courseModel.dto.ScoreDTO;
import com.courseModel.dto.TeachingDTO;
import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import com.courseModel.enums.TeachingStatus;
import com.courseModel.exception.BadRequestException;
import com.courseModel.exception.NotFoundException;
import com.courseModel.mapper.TeachingMapper;
import com.courseModel.repository.TeachingRepository;
import com.courseModel.repository.TeachingToScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        teaching.getScores().add(new TeachingToScore()
                .setTeachingId(teaching.getId())
                .setScore(score.getScore()));
        return repository.save(teaching);
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
