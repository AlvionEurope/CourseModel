package com.courseModel.controller.student;

import com.courseModel.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateStudentTest extends AbstractStudentTest {
    @Test
    void giveStudent_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Student studentBd = saveStudentToDB();
        mockMvc.perform(
                        put(PATH_WITH_GRADE_BOOK, studentBd.getGradeBook())
                                .content(objectMapper.writeValueAsString(new Student()
                                        .setGradeBook(studentBd.getGradeBook())
                                        .setName("Роман")
                                        .setAddress("Москва")
                                        .setPhone("8-920-456-12-33")
                                        .setEmail("first@ru")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gradeBook").value(studentBd.getGradeBook()))
                .andExpect(jsonPath("$.name").value("Роман"));
    }
}
