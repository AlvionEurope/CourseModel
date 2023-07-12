package me.rudnikov.backend.dto.create;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseCreateDto {
    private String name;
    private Integer number;
    private Float price;
}