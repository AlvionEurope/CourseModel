package com.courseModel.service;

import com.courseModel.entity.Teaching;
import com.courseModel.exception.NotFoundException;
import com.courseModel.repository.TeachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeachingServiceImpl implements TeachingService {
    final TeachingRepository repository;

    @Override
    public Teaching create(Teaching teaching) {
        return repository.save(teaching);
    }

    @Override
    public boolean delete(int studentGradeBook, int courseNumber) {
        Optional<Teaching> teaching = getTeaching(studentGradeBook, courseNumber);
        if (teaching.isEmpty()) {
            return false;
        }
        repository.delete(teaching.get());
        return true;
    }

    public Optional<Teaching> getTeaching(int studentGradeBook, int courseNumber) {
        return Optional.ofNullable(repository.findByStudentGradeBookAndCourseNumber(studentGradeBook, courseNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Курс с номером %d или студент с номером зачетной книжки %d не найден", courseNumber, studentGradeBook))));
    }
}
