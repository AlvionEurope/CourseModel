package example.controller;

import example.entity.Course;
import example.entity.Student;
import example.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        return ResponseEntity.of(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student Student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.saveStudent(Student));
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,
                                                 @RequestBody Student Student) {
        return ResponseEntity.ok(studentService.updateStudent(id, Student));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("{id}/can-enroll")
    public ResponseEntity<Boolean> canEnrollInCourse(@PathVariable int id, @RequestParam("course-id") int courseId) {
        return ResponseEntity.ok(studentService.canEnrollInCourse(id, courseId));
    }

    @GetMapping("{id}/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable int id) {
        return ResponseEntity.of(studentService.getStudent(id)
                .map(Student::getCourses));
    }
}
