package com.courseModel.controller.student;

import com.courseModel.entity.Student;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeleteStudentTest extends AbstractStudentTest {
    @Test
    void deleteStudentByGradeBook() throws Exception {
        Student student = saveStudentToDB();
        mockMvc.perform(
                        delete(PATH_WITH_GRADE_BOOK, student.getGradeBook()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deleteStudentByGradeBook_and_GetFalse() throws Exception {
        mockMvc.perform(
                        delete(PATH_WITH_GRADE_BOOK, 0))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
