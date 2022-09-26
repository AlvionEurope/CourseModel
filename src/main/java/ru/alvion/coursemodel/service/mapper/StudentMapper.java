package ru.alvion.coursemodel.service.mapper;

import org.mapstruct.*;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.service.dto.StudentDTO;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {}
