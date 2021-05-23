package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.entity.StudentCourse;
import ru.alex.courseModel.entity.StudentCourseId;
import ru.alex.courseModel.reposttory.StudentCourseRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Data
public class StudentCourseService {

    @Autowired
    private StudentCourseRepo studentCourseRepo;

    public  List<StudentCourse> getStudentCourses(Student student){
        List<StudentCourse> studentCourses = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseRepo.findAll()){
            if(studentCourse.getStudent().equals(student)){
                studentCourses.add(studentCourse);
            }
        }
        return studentCourses;
    }

    public List<StudentCourse> getStudentCourses(Course course){
        List<StudentCourse> studentCourses = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseRepo.findAll()){
            if(studentCourse.getCourse().equals(course)){
                studentCourses.add(studentCourse);
            }
        }
        return studentCourses;
    }

    public List<Course> getCurrentCourses(Student student) {
        List<Course> currentStudentCourses = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseRepo.findAll()){
            if(studentCourse.getStudent().equals(student) && !studentCourse.isFinished()){
                currentStudentCourses.add(studentCourse.getCourse());
            }
        }
        return currentStudentCourses;
    }


    public StudentCourse getStudentCourse(long studentId, long courseId){
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);

        return studentCourseRepo.findById(studentCourseId).get();


    }

}
