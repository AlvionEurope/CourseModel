package com.courseModel.controller.student;

import com.courseModel.entity.Student;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetStudentTest extends AbstractStudentTest {

    @Test
    void foundByGradeBook() throws Exception {
        Student studentBd = saveStudentToDB();
        mockMvc.perform(get(PATH_WITH_GRADE_BOOK, studentBd.getGradeBook()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gradeBook").value(studentBd.getGradeBook()))
                .andExpect(jsonPath("$.name").value(studentBd.getName()))
                .andExpect(jsonPath("$.address").value(studentBd.getAddress()))
                .andExpect(jsonPath("$.phone").value(studentBd.getPhone()))
                .andExpect(jsonPath("$.email").value(studentBd.getEmail()));
    }

    @Test
    void notFoundStudent() throws Exception {
        mockMvc.perform(get(PATH_WITH_GRADE_BOOK, 0))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Студент с зачетной книжкой номер 0 не найден"));
    }
}
