package ru.alvion.coursemodel.service.mapper;

import org.mapstruct.*;
import ru.alvion.coursemodel.domain.Course;
import ru.alvion.coursemodel.domain.Professor;
import ru.alvion.coursemodel.service.dto.CourseDTO;
import ru.alvion.coursemodel.service.dto.ProfessorDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfessorMapper extends EntityMapper<ProfessorDTO, Professor> {
    @Mapping(target = "courses", source = "courses", qualifiedByName = "courseIdSet")
    ProfessorDTO toDto(Professor s);

    @Mapping(target = "removeCourse", ignore = true)
    Professor toEntity(ProfessorDTO professorDTO);

    @Named("courseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CourseDTO toDtoCourseId(Course course);

    @Named("courseIdSet")
    default Set<CourseDTO> toDtoCourseIdSet(Set<Course> course) {
        return course.stream().map(this::toDtoCourseId).collect(Collectors.toSet());
    }
}
