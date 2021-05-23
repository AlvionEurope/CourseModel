package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.model.InstructorDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.InstructorRepo;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepo instructorRepo;
    @Autowired
    private CourseRepo courseRepo;

    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public List<Instructor> getAll(){
        return (List<Instructor>)instructorRepo.findAll();
    }

    public Instructor addCourse(long courseId, long instructorId){

        Instructor instructor = instructorRepo.findById(instructorId).get();
        instructor.addCourse(courseRepo.findById(courseId).get());
        return saveInstructor(instructor);
    }


//    public List<InstructorDto> getAll(){
//        List<InstructorDto> instructors = new ArrayList<>();
//        for(Instructor entity:instructorRepo.findAll()){
//            instructors.add(InstructorDto.toDto(entity));
//        }
//        return instructors;
//    }

//    public InstructorDto getOne(long id){
//        return InstructorDto.toDto(instructorRepo.findById(id).get());
//    }

//    public InstructorDto update(long id, Instructor instructor){
//        instructor.setId(id);
//        return saveInstructor(instructor);
//    }

    public long delete(long id){
        if(instructorRepo.existsById(id)) {
            instructorRepo.deleteById(id);
        }
        return id;
    }
}
