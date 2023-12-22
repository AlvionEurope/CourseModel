package com.courseModel.controller.teaching;

import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import com.courseModel.enums.TeachingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddScoreToTeachingTest extends AbstractTeachingTest {
    @Test
    void addScore() throws Exception {
        Teaching teaching = saveTeachingToDB();
        TeachingToScore teachingToScore = saveTeachingToScoreToDB(teaching);
        Teaching teaching1 = setStatusTeachingToDB(teaching, TeachingStatus.IN_PROGRESS);
        mockMvc.perform(
                        post(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(teachingToScore)))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(teachingRepository.findAll().get(0).getScores()).hasSize(2);
    }

    @Test
    void addScoreWithWrongStatus() throws Exception {
        Teaching teaching = saveTeachingToDB();
        TeachingToScore teachingToScore = saveTeachingToScoreToDB(teaching);
        Teaching teaching1 = setStatusTeachingToDB(teaching, TeachingStatus.NOT_STARTED);
        mockMvc.perform(
                        post(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(teachingToScore)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message")
                        .value("Оценки можно ставить если обучение в статусе IN_PROGRESS, текущий статус обучения NOT_STARTED"));
    }
}
