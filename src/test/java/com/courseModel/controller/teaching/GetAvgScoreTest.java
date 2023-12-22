package com.courseModel.controller.teaching;

import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import com.courseModel.enums.TeachingStatus;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAvgScoreTest extends AbstractTeachingTest {
    @Test
    void getAvgScore() throws Exception {
        Teaching teaching = saveTeachingToDB();
        teaching.setStatus(TeachingStatus.IN_PROGRESS);
        List<TeachingToScore> scores = new ArrayList<>();
        TeachingToScore teachingToScore = new TeachingToScore();
        teachingToScore.setDate(new Date(System.currentTimeMillis()));
        teachingToScore.setScore(5);
        scores.add(teachingToScore);
        teachingToScore.setTeachingId(teaching.getId());
        teachingToScoreRepository.save(teachingToScore);
        teaching.setScores(scores);
        Teaching teaching1 = teachingRepository.save(teaching);
        mockMvc.perform(
                        get(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageScore").value("5.0"));
    }

    @Test
    void getWrongAvgScore() throws Exception {
        Teaching teaching = saveTeachingToDB();
        teaching.setStatus(TeachingStatus.IN_PROGRESS);
        Teaching teaching1 = teachingRepository.save(teaching);
        mockMvc.perform(
                        get(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message")
                        .value("Обучение не содержит оценок"));
    }
}
