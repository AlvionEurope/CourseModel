package com.courseModel.mapper;

import com.courseModel.dto.TeachingDTO;
import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeachingMapper {
    public TeachingDTO convert(Teaching teaching) {
        return new TeachingDTO()
                .setCourseNumber(teaching.getCourse().getNumber())
                .setStudentGradeBook(teaching.getStudentGradeBook())
                .setStatus(teaching.getStatus())
                .setScores(teaching.getScores().stream().map(TeachingToScore::getScore).collect(Collectors.toList()))
                .setFinalScore(teaching.getFinalScore());
    }
}
