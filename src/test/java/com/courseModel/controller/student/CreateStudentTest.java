package com.courseModel.controller.student;

import com.courseModel.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CreateStudentTest extends AbstractStudentTest {

    @Test
    void createStudent() throws Exception {
        Student studentBd = saveStudentToDB();
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentBd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(studentBd.getName()))
                .andExpect(jsonPath("$.address").value(studentBd.getAddress()))
                .andExpect(jsonPath("$.phone").value(studentBd.getPhone()))
                .andExpect(jsonPath("$.email").value(studentBd.getEmail()));
    }

    @Test
    void createBadStudent() throws Exception {
        Student studentBd = saveStudentToDB();
        studentBd.setName(null);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentBd)))
                .andExpect(status().isBadRequest());
    }
}
