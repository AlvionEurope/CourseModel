package example.controller;

import example.entity.Course;
import example.entity.Teacher;
import example.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        return ResponseEntity.of(courseService.getCourse(id));
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.saveCourse(course));
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id,
                                               @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("{id}/students")
    public void addStudentToCourse(@PathVariable int id,
                                   @RequestBody Course course) {
        courseService.addStudent(id, course.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}/students/{studentId}")
    public void removeStudentFromCourse(@PathVariable int id,
                                        @PathVariable int studentId) {
        courseService.removeStudent(id, studentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("{id}/teachers")
    public void addTeacherToCourse(@PathVariable int id,
                                   @RequestBody Teacher teacher) {
        courseService.addTeacher(id, teacher.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}/teachers/{teacherId}")
    public void removeTeacherFromCourse(@PathVariable int id,
                                        @PathVariable int teacherId) {
        courseService.removeTeacher(id, teacherId);
    }
}
