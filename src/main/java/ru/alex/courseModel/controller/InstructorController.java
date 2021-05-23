package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.model.InstructorDto;
import ru.alex.courseModel.service.InstructorService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/all")
    public List<InstructorDto> getInstructors(){
        InstructorDto instructorDto = new InstructorDto();
        return instructorDto.getInstructorDtoList(instructorService.getAll());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity getInstructor(@PathVariable long id){
//        try{
//            return ResponseEntity.ok(instructorService.getOne(id));
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
//        }
//    }

    @PostMapping
    public ResponseEntity<String> addInstructor(@RequestBody Instructor instructor){
        try{
            instructorService.saveInstructor(instructor);
            return ResponseEntity.ok("Профессор успешно сохранен");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
        }
    }

    @GetMapping("/addCourse")
    public ResponseEntity addCourse(@RequestParam ("courseId") Long courseId,@RequestParam ("instructorId") Long instructorId){
        try{
            return ResponseEntity.ok(instructorService.addCourse(courseId, instructorId));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Что-то пошло не так :(");
        }
    }

}
