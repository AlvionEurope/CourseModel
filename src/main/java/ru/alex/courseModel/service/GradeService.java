package ru.alex.courseModel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.TrainingCourseRepository;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final TrainingCourseRepository trainingCourseRepository;
    private final GradeRepository gradeRepository;

    public void addGrade(long studentId, int courseId, Grade grade) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        TrainingCourse trainingCourse = trainingCourseRepository.getOne(id);
        trainingCourse.getGrades().add(grade);
        trainingCourseRepository.save(trainingCourse);
    }

    public void updateGradeById(Grade grade) {
        Grade newGrade = gradeRepository.getOne(grade.getId());
        newGrade.setValue(grade.getValue());
        gradeRepository.save(newGrade);
    }

    public void deleteGradeById(long id) {
        gradeRepository.deleteById(id);
    }
}
