package me.rudnikov.backend.dto.read;

import me.rudnikov.backend.dto.serializer.StudentSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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