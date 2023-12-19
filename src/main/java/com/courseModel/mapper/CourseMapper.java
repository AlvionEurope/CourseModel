package com.courseModel.mapper;

import com.courseModel.dto.CourseDTO;
import com.courseModel.dto.CreateCourseRequest;
import com.courseModel.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course convert(CreateCourseRequest request) {
        return new Course()
                .setName(request.getName())
                .setCost(request.getCost())
                .setProfessorId(request.getProfessorId());
    }

    public CourseDTO convert(Course course) {
        return new CourseDTO()
                .setNumber(course.getNumber())
                .setName(course.getName())
                .setCost(course.getCost())
                .setProfessorId(course.getProfessorId());
    }
}
