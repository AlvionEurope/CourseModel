package com.courseModel.controller.teaching;

import com.courseModel.controller.BaseTest;
import com.courseModel.entity.Course;
import com.courseModel.entity.Student;
import com.courseModel.entity.Teaching;
import com.courseModel.entity.TeachingToScore;
import com.courseModel.enums.TeachingStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractTeachingTest extends BaseTest {
    protected final static String PATH = "/v1/teaching";
    protected final static String GRADE_BOOK = "/student/{grade-book}";
    protected final static String COURSE_NUMBER = "/course/{course-number}";
    protected final static String PATH_WITH_COURSE_NUMBER_AND_GRADE_BOOK = PATH + COURSE_NUMBER + GRADE_BOOK;

    protected Teaching saveTeachingToDB() {
        Student student = new Student()
                .setName("Сережа")
                .setAddress("Москва")
                .setPhone("8-920-565-54-52")
                .setEmail("first@ru");
        studentRepository.save(student);
        Course course = new Course()
                .setName("JS")
                .setCost(135000);
        courseRepository.save(course);
        Teaching teaching = new Teaching()
                .setStudentGradeBook(student.getGradeBook())
                .setCourse(course)
                .setFinalScore(null);
        return teachingRepository.save(teaching);
    }

    protected Teaching setStatusTeachingToDB(Teaching teaching, TeachingStatus status) {
        teaching.setStatus(status);
        List<TeachingToScore> scores = new ArrayList<>();
        TeachingToScore teachingToScore = saveTeachingToScoreToDB(teaching);
        scores.add(teachingToScore);
        teaching.setScores(scores);
        return teachingRepository.save(teaching);
    }

    protected TeachingToScore saveTeachingToScoreToDB(Teaching teaching) {
        TeachingToScore teachingToScore = new TeachingToScore();
        teachingToScore.setDate(new Date(System.currentTimeMillis()));
        teachingToScore.setScore(5);
        teachingToScore.setTeachingId(teaching.getId());
        return teachingToScore;
    }
}
