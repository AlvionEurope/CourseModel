package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentCourseDto {
    private StudentCourseId id;
    private boolean isFinished;
    private String studentName;
    private String courseName;
    private List<GradeDto> grades = new ArrayList<>();

    public List<GradeDto> getGrades(List<Grade> grades){
        List<GradeDto> gradeDtoList = new ArrayList<>();

        for(Grade grade : grades) {
            GradeDto gradeDto = new GradeDto();

            gradeDto.setId(gradeDto.getId());
            gradeDto.setValue(grade.getValue());

            gradeDtoList.add(gradeDto);
        }
        return gradeDtoList;
    }



}
