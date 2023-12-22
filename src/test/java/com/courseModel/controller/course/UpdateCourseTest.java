package com.courseModel.controller.course;

import com.courseModel.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateCourseTest extends AbstractCourseTest {
    @Test
    void giveCourse_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Course course = saveCourseToDB();
        mockMvc.perform(
                        put(PATH_WITH_NUMBER, course.getNumber())
                                .content(objectMapper.writeValueAsString(new Course()
                                        .setNumber(course.getNumber())
                                        .setName("Java")
                                        .setCost(126000F)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(course.getNumber()))
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.cost").value("126000.0"));
    }
}
