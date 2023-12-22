package com.courseModel.controller.professor;

import com.courseModel.entity.Professor;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetProfessorTest extends AbstractProfessorTest {

    @Test
    void foundById() throws Exception {
        Professor professor = saveProfessorToDB();
        mockMvc.perform(get(PATH_WITH_ID, professor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(professor.getId()))
                .andExpect(jsonPath("$.name").value(professor.getName()))
                .andExpect(jsonPath("$.address").value(professor.getAddress()))
                .andExpect(jsonPath("$.phone").value(professor.getPhone()))
                .andExpect(jsonPath("$.payment").value(professor.getPayment()));
    }

    @Test
    void notFoundProfessor() throws Exception {
        mockMvc.perform(get(PATH_WITH_ID, 0))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Профессор с id 0 не найден"));
    }
}
