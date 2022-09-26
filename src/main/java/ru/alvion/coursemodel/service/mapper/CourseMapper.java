package ru.alvion.coursemodel.service.mapper;

import org.mapstruct.*;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.service.dto.CourseDTO;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {}
