package ru.alvion.coursemodel.service.mapper;

import org.mapstruct.*;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.GradeAssignment;
import ru.alvion.coursemodel.service.dto.CourseAssignmentDTO;
import ru.alvion.coursemodel.service.dto.GradeAssignmentDTO;

@Mapper(componentModel = "spring")
public interface GradeAssignmentMapper extends EntityMapper<GradeAssignmentDTO, GradeAssignment> {
    @Mapping(target = "courseAssignment", source = "courseAssignment", qualifiedByName = "courseAssignmentId")
    GradeAssignmentDTO toDto(GradeAssignment s);

    @Named("courseAssignmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CourseAssignmentDTO toDtoCourseAssignmentId(CourseAssignment courseAssignment);
}
