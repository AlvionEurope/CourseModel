package me.rudnikov.backend.dto.read;

import me.rudnikov.backend.dto.serializer.ProfessorSerializer;

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
        using = ProfessorSerializer.class
)
public class ProfessorDto {
        private Long id;
        private String fullName;
        private String address;
        private String phoneNumber;
        private Float payment;
        private List<CourseDto> courses;
}