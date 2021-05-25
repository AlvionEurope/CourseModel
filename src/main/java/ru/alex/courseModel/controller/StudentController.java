package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
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

    @Autowired
    private StudentCourseService studentCourseService;

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
    public StudentDto updateStudent(@PathVariable long id, @RequestBody Student student){
        return new StudentDto(studentService.updateStudent(id, student));
    }

    @GetMapping("/addcourse")
    public void addCourse(@RequestParam ("courseId") long courseId,@RequestParam ("studentId") long studentId){
        studentService.addStudentCourse(studentService.getStudentById(studentId), courseService.getCourseById(courseId));
    }

}


//    @GetMapping
//    public List<StudentDto> stud (){
//        Student student = studentService.getStudentById(4);
//        Course course = courseService.getCourseById(22);
//        StudentCourseId id = new StudentCourseId(student.getId(), course.getId());
////        student.set
////        student.set
////        student.getStudentCourse(id);
//        student.addCourse(course);
//
////        student.getStudentCourse(id).addGrade(new Grade(2));
////        student.getStudentCourse(id).addGrade(new Grade(3));
////        student.getStudentCourse(id).addGrade(new Grade(4));
////        student.getStudentCourse(id).addGrade(new Grade(5));
//
//
//        studentService.saveStudent(student);
//
//        StudentDto studentDto = new StudentDto();
//        return studentDto.getStudentDtoList(studentService.getAllStudents());
//    }






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
