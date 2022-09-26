package ru.alvion.coursemodel.service.mapper;

import org.mapstruct.*;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.service.dto.CourseAssignmentDTO;
import ru.alvion.coursemodel.service.dto.CourseDTO;
import ru.alvion.coursemodel.service.dto.StudentDTO;

@Mapper(componentModel = "spring")
public interface CourseAssignmentMapper extends EntityMapper<CourseAssignmentDTO, CourseAssignment> {
    @Mapping(target = "student", source = "student", qualifiedByName = "studentId")
    @Mapping(target = "course", source = "course", qualifiedByName = "courseId")
    CourseAssignmentDTO toDto(CourseAssignment s);

    @Named("studentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StudentDTO toDtoStudentId(Student student);

    @Named("courseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CourseDTO toDtoCourseId(Course course);
}
