package ru.wxw.coursemodel.wxw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.wxw.coursemodel.wxw.entity.CourseStage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
    private int gradeBook;
    private float academicPerformance;
}
