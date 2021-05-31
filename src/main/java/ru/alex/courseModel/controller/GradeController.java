package ru.alex.courseModel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Grade;
import ru.alex.courseModel.service.GradeService;
import ru.alex.courseModel.service.TrainingCourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grades")
public class GradeController {

    private final TrainingCourseService trainingCourseService;
    private final GradeService gradeService;

    @GetMapping("/all")
    public List<Grade> getGradesOfStudentCourse(@RequestParam("studentId") long studentId,
                                                @RequestParam("courseId") int courseId) {
        return trainingCourseService.getTrainingCourse(courseId, studentId).getGrades();
    }

    @PostMapping
    public void addGrade(@RequestParam("studentId") long studentId,
                         @RequestParam("courseId") int courseId,
                         @RequestBody Grade grade) {
        gradeService.addGrade(studentId, courseId, grade);
    }

    @PutMapping
    public void updateGrade(@RequestBody Grade grade) {
        gradeService.updateGradeById(grade);
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable long id) {
        gradeService.deleteGradeById(id);
    }
}
