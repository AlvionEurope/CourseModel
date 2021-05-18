package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.model.Student;
import ru.alex.courseModel.reposttory.StudentRepo;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping
    public ResponseEntity addStudent(@RequestBody Student student){
        try{
            System.out.println(student);
            studentRepo.save(student);
            return ResponseEntity.ok("Студент успешно сохранен");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
        }
    }

    @GetMapping
    public ResponseEntity getStudents(){
        try{
            return ResponseEntity.ok("Заработало");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
        }
    }


}
