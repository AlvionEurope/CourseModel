package com.courseModel.controller.teaching;

import com.courseModel.entity.Teaching;

import com.courseModel.enums.TeachingStatus;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FinishTeachingTest extends AbstractTeachingTest {
    @Test
    void finishTeaching() throws Exception {
        Teaching teaching = saveTeachingToDB();
        Teaching teaching1 = setStatusTeachingToDB(teaching, TeachingStatus.IN_PROGRESS);
        mockMvc.perform(
                        post(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/final-score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentGradeBook").value(teaching1.getStudentGradeBook()))
                .andExpect(jsonPath("$.courseNumber").value(teaching1.getCourse().getNumber()))
                .andExpect(jsonPath("$.status").value("FINISHED"))
                .andExpect(jsonPath("$.scores.size()").value(teaching1.getScores().size()))
                .andExpect(jsonPath("$.finalScore").value("5"));
    }

    @Test
    void finishTeachingWithWrongStatus() throws Exception {
        Teaching teaching = saveTeachingToDB();
        teaching.setStatus(TeachingStatus.NOT_STARTED);
        Teaching teaching1 = teachingRepository.save(teaching);
        mockMvc.perform(
                        post(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK + "/final-score"
                                , teaching1.getCourse().getNumber(), teaching1.getStudentGradeBook()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message")
                        .value("Нельзя завершить обучение если оно не в статусе IN_PROGRESS, текущий статус обучения NOT_STARTED"));
    }
}
