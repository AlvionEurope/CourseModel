package ru.alex.courseModel.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.reposttory.CourseRepo;
import ru.alex.courseModel.reposttory.GradeRepo;
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
    @Autowired
    private GradeRepo gradeRepo;

    public List<Course> getAvailableCourse (long studentId){
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepo.findAll()){
            if (!course.isPresent(studentId)){
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> getAllStudentCourses (long studentId){
        List<Course> studentCourses = new ArrayList<>();
        studentCourses.addAll(getFinishedCourses(studentId));
        studentCourses.addAll(getCurrentCourses(studentId));
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

    public void addStudentCourse(int courseId, long studentId){
        Student student = studentRepo.findById(studentId).get();
        Course course = courseRepo.findById(courseId).get();
        new StudentCourse(student, course);
        studentRepo.save(student);
    }

    public void removeStudentCourse(int courseId, long studentId){
        Student student = studentRepo.findById(studentId).get();
        Course course = courseRepo.findById(courseId).get();
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        studentCourseRepo.findById(id).get().removeCourse(student, course);
        studentCourseRepo.deleteById(id);
    }

    public List<Student> getAllCourseStudents (int courseId){
        List<Student> courseStudents = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseRepo.findAll()){
            if (studentCourse.getCourse().getId() == courseId){
                courseStudents.add(studentCourse.getStudent());
            }
        }
        return courseStudents;
    }





    public  List<Grade> getGrades (StudentCourse studentCourse){
        return studentCourse.getGrades();
    }

    public Grade getGradeById(long id){
        return gradeRepo.findById(id).get();
    }

    public void addGrade(StudentCourseId id, int value){
        StudentCourse studentCourse = studentCourseRepo.findById(id).get();
        studentCourse.addGrade(new Grade(value));
        studentCourseRepo.save(studentCourse);
    }

    public Grade updateGradeById(long id, int newGradeValue){
        Grade grade = getGradeById(id);
        grade.setValue(newGradeValue);
        return gradeRepo.save(grade);
    }

    public void deleteGradeById(long id){
        gradeRepo.deleteById(id);
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
