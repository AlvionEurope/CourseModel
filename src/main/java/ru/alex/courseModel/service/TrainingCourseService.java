package ru.alex.courseModel.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepository;
import ru.alex.courseModel.reposttory.GradeRepository;
import ru.alex.courseModel.reposttory.TrainingCourseRepository;
import ru.alex.courseModel.reposttory.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class TrainingCourseService {

    private final TrainingCourseRepository trainingCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public TrainingCourse getTrainingCourse(int courseId, long studentId) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        return trainingCourseRepository.getOne(id);
    }

    public void removeTrainingCourse(int courseId, long studentId) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        trainingCourseRepository.deleteById(id);
    }

    public float getAverageGrade(long studentId, int courseId) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        List<Grade> grades = trainingCourseRepository.getOne(id).getGrades();
        return 1f * grades.stream().mapToInt(Grade::getValue).sum() / grades.size();
    }

    public void finalizeTrainingCourse(TrainingCourseId trainingCourseId, Integer finalGrade) {
        TrainingCourse trainingCourse = trainingCourseRepository.getOne(trainingCourseId);
        if (trainingCourse.getFinalGrade() == null && finalGrade != null) {
            trainingCourse.setFinalGrade(finalGrade);
            trainingCourse.setFinished(true);
        }
        trainingCourseRepository.save(trainingCourse);
    }

    public Integer getFinalGrade(long studentId, int courseId) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        return trainingCourseRepository.getOne(id).getFinalGrade();
    }

    public List<Course> getFinishedStudentCourses(long studentId, boolean isFinished) {
        List<TrainingCourse> studentCourses = trainingCourseRepository.findAllByIsFinishedIsAndStudent_Id(isFinished, studentId);
        return studentCourses.stream().map(TrainingCourse::getCourse).collect(Collectors.toList());
    }

    public List<Course> getCoursesFromStudent(long studentId) {
        List<TrainingCourse> studentCourses = trainingCourseRepository.findAllByStudent_Id(studentId);
        return studentCourses.stream().map(TrainingCourse::getCourse).collect(Collectors.toList());
    }

    public List<Student> getStudentsFromCourse(int courseId) {
        List<TrainingCourse> courseStudents = trainingCourseRepository.findAllByCourse_Id(courseId);
        return courseStudents.stream().map(TrainingCourse::getStudent).collect(Collectors.toList());
    }
}
