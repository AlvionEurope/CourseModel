package com.courseModel.controller.course;

import com.courseModel.entity.Course;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetCourseTest extends AbstractCourseTest {

    @Test
    void foundByNumber() throws Exception {
        Course course = saveCourseToDB();
        mockMvc.perform(get(PATH_WITH_NUMBER, course.getNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(course.getNumber()))
                .andExpect(jsonPath("$.name").value(course.getName()))
                .andExpect(jsonPath("$.cost").value(course.getCost()));
    }

    @Test
    void notFoundCourse() throws Exception {
        mockMvc.perform(get(PATH_WITH_NUMBER, 0))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("Курс с номером 0 не найден"));
    }
}
