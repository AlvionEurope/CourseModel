package example.controller;

import example.entity.Teacher;
import example.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) {
        return ResponseEntity.of(teacherService.getTeacher(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher Teacher) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teacherService.saveTeacher(Teacher));
    }

    @PutMapping("{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id,
                                                 @RequestBody Teacher Teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, Teacher));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
    }
}
