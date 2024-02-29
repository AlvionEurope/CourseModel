package com.courseModel.controller.course;

import com.courseModel.entity.Course;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteCourseTest extends AbstractCourseTest {
    @Test
    void deleteCourseByNumber() throws Exception {
        Course course = saveCourseToDB();
        mockMvc.perform(
                        delete(PATH_WITH_NUMBER, course.getNumber()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deleteCourseByNumber_and_GetFalse() throws Exception {
        mockMvc.perform(
                        delete(PATH_WITH_NUMBER, 0))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
