package ru.alex.courseModel.controller;

import antlr.PreservingFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.GradeDto;
import ru.alex.courseModel.model.StudentCourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.CourseService;
import ru.alex.courseModel.service.StudentCourseService;
import ru.alex.courseModel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<StudentDto> getStudents() {
        StudentDto studentDto = new StudentDto();
        return studentDto.getStudentDtoList(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable long id) {
        return new StudentDto(studentService.getStudentById(id));
    }

    @PostMapping("/add")
    public StudentDto addStudent(@RequestBody Student student) {
        return new StudentDto(studentService.saveStudent(student));
    }

    @DeleteMapping("/del-{id}")
    public long deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public StudentDto updateStudent(@PathVariable long id,
                                    @RequestBody Student student){
        return new StudentDto(studentService.updateStudent(id, student));
    }

    @GetMapping("/addcourse")
    public void addCourse(@RequestParam ("courseId") long courseId,
                          @RequestParam ("studentId") long studentId){
        studentService.addStudentCourse(studentService.getStudentById(studentId), courseService.getCourseById(courseId));
    }

    @GetMapping("/current-courses")
    public List<CourseDto> allCurrentCourses(@RequestParam ("studentId") long studentId){
        CourseDto courseDto = new CourseDto();
        return courseDto.getCourseDtoList(studentService.getCurrentCourses(studentId));
    }

    @GetMapping("/finished-courses")
    public List<CourseDto> allFinishedCourses(@RequestParam ("studentId") long studentId){
        CourseDto courseDto = new CourseDto();
        return courseDto.getCourseDtoList(studentService.getFinishedCourses(studentId));
    }

    @GetMapping("/grades")
    public List<GradeDto> allGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") long courseId){
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        GradeDto gradeDto = new GradeDto();
        return gradeDto.getGradeDtoList(studentService.getStudentCourse(id).getGrades());
    }

    @GetMapping("/grades/add")
    public List<GradeDto> addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") long courseId,
                                   @RequestParam ("grade") int grade){
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        studentService.addGrade(id, grade);
        GradeDto gradeDto = new GradeDto();
        return gradeDto.getGradeDtoList(studentService.getStudentCourse(id).getGrades());
    }




}

//    @GetMapping("/all")
//    public ResponseEntity getStudents(){
//        try{
//            return ResponseEntity.ok(studentService.getAll());
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
//        }
//    }



//
//    @GetMapping("/get-{id}")
//    public ResponseEntity getStudent(@PathVariable long id){
//        try{
//            return ResponseEntity.ok(studentService.getOne(id));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Пользователь заблудился по дороге :(");
//        }
//    }

//    @GetMapping("/current-course/{id}")
//    public ResponseEntity getCurrentCourses(@PathVariable long id){
//        try{
//            return ResponseEntity.ok(studentService.getCurrentCourses(id));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка!");
//        }
//    }

//    @GetMapping("/available-course/{id}")
//    public ResponseEntity getAvailableCourses(@PathVariable long id){
//        try{
//            return ResponseEntity.ok(studentService.getAvailableCourses(id));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка!");
//        }
//    }

//    @GetMapping("/completed-course/{id}")
//    public ResponseEntity getCompletedCourses(@PathVariable long id){
//        try{
//            return ResponseEntity.ok(studentService.getCompletedCourses(id));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка!");
//        }
//    }
