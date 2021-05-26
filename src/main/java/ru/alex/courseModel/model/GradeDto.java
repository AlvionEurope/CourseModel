package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Grade;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GradeDto {
    private long id;
    private int value;

    public GradeDto(Grade grade) {
        this.id = grade.getId();
        this.value = grade.getValue();
    }

    public static List<GradeDto> getGradeDtoList (List<Grade> grades){
        if (grades == null) {
            return new ArrayList<>();
        }
        List<GradeDto> gradeDtoList = new ArrayList<>();
        for (Grade grade : grades){
            gradeDtoList.add(new GradeDto(grade));
        }
        return gradeDtoList;
    }
}
