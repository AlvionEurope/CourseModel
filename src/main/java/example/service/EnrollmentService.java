package example.service;


import example.entity.EnrollmentGrade;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentGrade> getGrades();

    List<EnrollmentGrade> getGrades(int studentId);

    List<EnrollmentGrade> getGrades(int studentId, int courseId);

    float getAverageGrade(int studentId);

    float getAverageGrade(int studentId, int courseId);

    float getFinalGrade(int studentId, int courseId);

    EnrollmentGrade addGrade(int studentId, int courseId, float grade);

    EnrollmentGrade updateGrade(long gradeId, float grade);

    void removeGrade(long gradeId);
}
