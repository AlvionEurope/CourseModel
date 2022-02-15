package example.controller;

import example.dto.GradeDto;
import example.entity.EnrollmentGrade;
import example.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("grades")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@RequestBody GradeDto grade) {
        EnrollmentGrade enrollmentGrade = enrollmentService.addGrade(
                grade.getStudentId(), grade.getCourseId(), grade.getGrade());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(enrollmentGrade, GradeDto.class));
    }

    @PutMapping("{id}")
    public ResponseEntity<GradeDto> updateEnrollment(@PathVariable int id,
                                                     @RequestBody GradeDto grade) {
        EnrollmentGrade enrollmentGrade = enrollmentService.updateGrade(id, grade.getGrade());
        return ResponseEntity.ok(modelMapper.map(enrollmentGrade, GradeDto.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteEnrollment(@PathVariable int id) {
        enrollmentService.removeGrade(id);
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getGrades(
            @RequestParam("student-id") Optional<Integer> studentId,
            @RequestParam("course-id") Optional<Integer> courseId) {

        List<EnrollmentGrade> grades = studentId.map(s ->
                courseId.map(c -> enrollmentService.getGrades(s, c))
                        .orElseGet(() -> enrollmentService.getGrades(s))
        ).orElseGet(enrollmentService::getGrades);

        return ResponseEntity.ok(grades.stream()
                .map(g -> modelMapper.map(g, GradeDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("average")
    public ResponseEntity<Float> getAverageGrade(
            @RequestParam("student-id") int studentId,
            @RequestParam("course-id") Optional<Integer> courseId) {

        return ResponseEntity.ok(courseId.map(c ->
                        enrollmentService.getAverageGrade(studentId, c))
                .orElseGet(() -> enrollmentService.getAverageGrade(studentId)));
    }

    @GetMapping("final")
    public ResponseEntity<Float> getFinalGrade(
            @RequestParam("student-id") int studentId,
            @RequestParam("course-id") int courseId) {

        return ResponseEntity.ok(enrollmentService.getFinalGrade(studentId, courseId));
    }
}
