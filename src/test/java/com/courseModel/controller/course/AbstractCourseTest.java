package com.courseModel.controller.course;

import com.courseModel.controller.BaseTest;
import com.courseModel.entity.Course;

public class AbstractCourseTest extends BaseTest {
    protected final static String PATH = "/v1/course";
    protected final static String PATH_WITH_NUMBER = PATH + "/{number}";

    protected Course saveCourseToDB() {
        Course course = new Course()
                .setName("JS")
                .setCost(135000);
        return courseRepository.save(course);
    }
}
