package com.courseModel.controller.teaching;

import com.courseModel.entity.Teaching;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetTeachingTest extends AbstractTeachingTest {

    @Test
    void foundByGradeBookAndCourseNumber() throws Exception {
        Teaching teaching = saveTeachingToDB();
        mockMvc.perform(
                        get(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK, teaching.getCourse().getNumber(), teaching.getStudentGradeBook()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentGradeBook").value(teaching.getStudentGradeBook()))
                .andExpect(jsonPath("$.courseNumber").value(teaching.getCourse().getNumber()))
                .andExpect(jsonPath("$.status").value(teaching.getStatus()))
                .andExpect(jsonPath("$.scores").isEmpty())
                .andExpect(jsonPath("$.finalScore").value(teaching.getFinalScore()));
    }

    @Test
    void notFoundTeaching() throws Exception {
        mockMvc.perform(get(PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK, 0, 0))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Не найдено обучение студента с зачетной книжкой 0 на курсе номер 0"));
    }
}
