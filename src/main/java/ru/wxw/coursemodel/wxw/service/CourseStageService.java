package ru.wxw.coursemodel.wxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wxw.coursemodel.wxw.dto.AcademicPerformanceDTO;
import ru.wxw.coursemodel.wxw.dto.FinalGradeDTO;
import ru.wxw.coursemodel.wxw.entity.CourseStage;
import ru.wxw.coursemodel.wxw.entity.CourseStudent;
import ru.wxw.coursemodel.wxw.repository.CourseStageRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseStageService {
    @Autowired
    private final CourseStageRepository courseStageRepository;
    @Autowired
    private final CourseStudentService courseStudentService;
    public CourseStageService(CourseStageRepository courseStageRepository, CourseStudentService courseStudentService) {
        this.courseStageRepository = courseStageRepository;
        this.courseStudentService = courseStudentService;
    }
    public AcademicPerformanceDTO getPerformance(Long id, Long courseId) {
        List<CourseStage> courseStageList = courseStageRepository.findAll();
        double grade = courseStageList.stream()
                .filter(p-> p.getStudent().getId().equals(id) && p.getCourse().getId().equals(courseId))
                .mapToDouble(CourseStage::getGrade)
                .average().getAsDouble();
        return new AcademicPerformanceDTO( (float) grade, courseId, id);
    }
    public FinalGradeDTO getFinalGrade(Long studentId, Long courseId) {
        List<CourseStage> courseStageList = courseStageRepository.findAll();
        CourseStage courseStage = courseStageList.stream()
                    .filter(p ->p.getStudent().getId().equals(studentId))
                .findFirst().orElse(null);
        if(courseStage != null) {
            int gradeBook = courseStage.getStudent().getGradeBook();
            List<CourseStudent> courseStudentList = courseStudentService.findAll();
            CourseStudent courseStudent = courseStudentList.stream()
                    .filter(p->p.getStudentGradeBook()==gradeBook && p.getCourse().getId().equals(courseId))
                    .findFirst().orElse(null);
            if (courseStudent != null) {
                if(courseStudent.isFinished()) {
                    double grade = courseStageList.stream()
                            .filter(p ->p.getStudent().getId().equals(studentId) && p.getCourse().getId().equals(courseId))
                            .mapToDouble(CourseStage::getGrade)
                            .average().getAsDouble();
                    return new FinalGradeDTO("Course is complete", (float) grade, courseId, studentId);

                }
            }
        }
        return new FinalGradeDTO("Course not completed", 0f, courseId, studentId);
    }
}
