package me.rudnikov.backend.dto.create;

import lombok.*;

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