package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.InstructorDto;
import ru.alex.courseModel.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/all")
    public List<InstructorDto> getInstructors(){
        return InstructorDto.getInstructorDtoList(instructorService.getAll());
    }

    @GetMapping("/{id}")
    public InstructorDto getInstructorById(@PathVariable long id) {
        return new InstructorDto(instructorService.getInstructorById(id));
    }

    @PostMapping("/add")
    public InstructorDto addInstructor(@RequestBody Instructor instructor) {
        return new InstructorDto(instructorService.saveInstructor(instructor));
    }

    @DeleteMapping("/del-{id}")
    public long deleteInstructor(@PathVariable long id){
        instructorService.deleteInstructor(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public InstructorDto updateInstructor(@PathVariable long id, @RequestBody Instructor instructor){
        return new InstructorDto(instructorService.updateInstructor(id, instructor));
    }
}
