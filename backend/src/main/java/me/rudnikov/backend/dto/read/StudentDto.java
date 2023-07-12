package me.rudnikov.backend.dto.read;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import me.rudnikov.backend.dto.serializer.StudentSerializer;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonSerialize(
        using = StudentSerializer.class
)
public class StudentDto {
    private Long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer recordBook;
    private Float averagePerformance;
    private List<CourseProgressDto> courseProgressList;
}