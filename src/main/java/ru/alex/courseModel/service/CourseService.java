package ru.alex.courseModel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.reposttory.CourseRepo;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAll(){
        return (List<Course>)courseRepo.findAll();
    }

    public Course getCourseById (long id){
        return courseRepo.findById(id).get();
    }
}
