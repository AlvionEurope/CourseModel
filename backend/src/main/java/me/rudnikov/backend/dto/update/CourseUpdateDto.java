package me.rudnikov.backend.dto.update;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDto {
    private String name;
    private Integer number;
    private Float price;
    private Long professorId;
}
