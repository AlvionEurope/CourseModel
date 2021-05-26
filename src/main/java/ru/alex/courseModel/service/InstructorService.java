package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.InstructorRepo;

import java.util.List;

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

    public void addCourse(int courseId, long instructorId){
        Instructor instructor = instructorRepo.findById(instructorId).get();
        Course course = courseRepo.findById(courseId).get();
        instructor.addCourse(course);
        course.addInstructor(instructor);
        saveInstructor(instructor);
    }

    public void removeCourse(int courseId, long instructorId){
        Instructor instructor = instructorRepo.findById(instructorId).get();
        Course course = courseRepo.findById(courseId).get();
        instructor.removeCourse(course);
        course.removeInstructor(instructor);
        saveInstructor(instructor);
    }

    public Instructor getInstructorById (long id){
        return instructorRepo.findById(id).get();
    }

    public Instructor updateInstructor (long id, Instructor instructor){
        instructor.setId(id);
        return saveInstructor(instructor);
    }

    public void deleteInstructor (long id){
        if(instructorRepo.existsById(id)) {
            instructorRepo.deleteById(id);
        }
    }
}
