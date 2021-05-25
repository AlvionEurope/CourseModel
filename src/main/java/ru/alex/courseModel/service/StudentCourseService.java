package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.StudentCourseRepo;
import ru.alex.courseModel.reposttory.StudentRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class StudentCourseService {

    @Autowired
    private StudentCourseRepo studentCourseRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudentRepo studentRepo;

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

    public StudentCourse getStudentCourse(StudentCourseId id){
        return studentCourseRepo.findById(id).get();
    }

    private List<Course> getCourses(long studentId, boolean finished){
        List<Course> currentCourses = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseRepo.findAll()){
            if(studentCourse.getStudent().getId() == studentId && studentCourse.isFinished() == finished){
                currentCourses.add(studentCourse.getCourse());
            }
        }
        return currentCourses;
    }

    public List<Course> getCurrentCourses(long studentId) {
        return getCourses(studentId, false);
    }

    public List<Course> getFinishedCourses(long studentId){
        return getCourses(studentId, true);
    }

    public void addStudentCourse(Student student, Course course){
        new StudentCourse(student, course);
        studentRepo.save(student);
    }

    public void removeStudentCourse(Student student, Course course){
        StudentCourseId id = new StudentCourseId(student.getId(), course.getId());
        studentCourseRepo.findById(id).get().removeCourse(student, course);
        studentCourseRepo.deleteById(id);
    }

    public  List<Grade> getGrades (StudentCourse studentCourse){
        return studentCourse.getGrades();
    }

    public Grade getGradeById(StudentCourse studentCourse, int id){
        return studentCourse.getGrades().get(id);
    }

    public void addGrade(StudentCourseId id, int value){
        StudentCourse studentCourse = studentCourseRepo.findById(id).get();
        studentCourse.addGrade(new Grade(value));
        studentCourseRepo.save(studentCourse);
    }

    public void updateGradeById(StudentCourse studentCourse, int id, int newGradeValue){
        Grade grade = getGradeById(studentCourse, id);
        grade.setValue(newGradeValue);
        getGrades(studentCourse).set(id, grade);
    }

    public void deleteGradeById(StudentCourse studentCourse, int id){
        studentCourse.removeGrade(getGrades(studentCourse).get(id));
    }

    public void setFinishedCourse(StudentCourse studentCourse, int finalGrade){
        studentCourse.setFinished(true);
        studentCourse.setFinalGrade(finalGrade);
    }

    public void updateFinalGrade (StudentCourse studentCourse, int finalGrade){
        if (!studentCourse.isFinished()) {
            studentCourse.setFinalGrade(finalGrade);
        }
    }

    public int getFinalGrade (StudentCourse studentCourse){
        return studentCourse.getFinalGrade();
    }

    public float getCurrentAverageGrade (StudentCourse studentCourse){
        if (studentCourse.isFinished()){
            return getFinalGrade(studentCourse);
        }
        return 1f * studentCourse.getGrades().stream().mapToInt(Grade::getValue).sum()
                / studentCourse.getGrades().size();
    }
}
