package com.courseModel.controller.course;

import com.courseModel.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCourseTest extends AbstractCourseTest {

    @Test
    void createCourse() throws Exception {
        Course course = saveCourseToDB();
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(course.getName()))
                .andExpect(jsonPath("$.cost").value(course.getCost()));
    }

    @Test
    void createBadCourse() throws Exception {
        Course course = saveCourseToDB();
        course.setName(null);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isBadRequest());
    }
}
