package com.courseModel.controller.professor;

import com.courseModel.entity.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateProfessorTest extends AbstractProfessorTest {

    @Test
    void createProfessor() throws Exception {
        Professor professor = saveProfessorToDB();
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(professor.getName()))
                .andExpect(jsonPath("$.address").value(professor.getAddress()))
                .andExpect(jsonPath("$.phone").value(professor.getPhone()))
                .andExpect(jsonPath("$.payment").value(professor.getPayment()));
    }

    @Test
    void createBadProfessor() throws Exception {
        Professor professor = saveProfessorToDB();
        professor.setName(null);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isBadRequest());
    }
}
