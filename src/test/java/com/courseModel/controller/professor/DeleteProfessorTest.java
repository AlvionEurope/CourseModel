package com.courseModel.controller.professor;

import com.courseModel.entity.Professor;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteProfessorTest extends AbstractProfessorTest {
    @Test
    void deleteProfessorById() throws Exception {
        Professor professor = saveProfessorToDB();
        mockMvc.perform(
                        delete(PATH_WITH_ID, professor.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deleteProfessorById_and_GetFalse() throws Exception {
        mockMvc.perform(
                        delete(PATH_WITH_ID, 0))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
