package com.courseModel.controller.professor;

import com.courseModel.entity.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateProfessorTest extends AbstractProfessorTest {
    @Test
    void giveProfessor_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Professor professor = saveProfessorToDB();
        mockMvc.perform(
                        put(PATH_WITH_ID, professor.getId())
                                .content(objectMapper.writeValueAsString(new Professor()
                                        .setId(professor.getId())
                                        .setName("Эдуард")
                                        .setAddress("Вологда")
                                        .setPhone("8-915-145-77-88")
                                        .setPayment(55000F)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(professor.getId()))
                .andExpect(jsonPath("$.name").value("Эдуард"))
                .andExpect(jsonPath("$.address").value("Вологда"))
                .andExpect(jsonPath("$.phone").value("8-915-145-77-88"))
                .andExpect(jsonPath("$.payment").value("55000.0"));
    }
}
