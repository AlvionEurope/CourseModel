package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActuveCourseDto {
    private StudentCourseId id;
    private boolean isFinished;
    private int finalGrade;
    private String studentName;
    private String courseName;
    private List<GradeDto> grades = new ArrayList<>();

    public ActuveCourseDto(ActiveCourse activeCourse) {
        this.id = activeCourse.getId();
        this.studentName = activeCourse.getStudent().getName();
        this.courseName = activeCourse.getCourse().getName();
        this.isFinished = activeCourse.isFinished();
        this.finalGrade = activeCourse.getFinalGrade();
    }

    public static List<GradeDto> getGrades(List<Grade> grades){
        List<GradeDto> gradeDtoList = new ArrayList<>();
        if (grades != null) {
            for (Grade grade : grades) {
                GradeDto gradeDto = new GradeDto();
                gradeDto.setId(gradeDto.getId());
                gradeDto.setValue(grade.getValue());
                gradeDtoList.add(gradeDto);
            }
        }
        return gradeDtoList;
    }



}
