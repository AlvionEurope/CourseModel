package com.courseModel.controller.teachingToScore;

import com.courseModel.controller.BaseTest;
import com.courseModel.entity.Student;


public abstract class AbstractTeachingToScoreTest extends BaseTest {
    protected final static String PATH = "/v1/student";
    protected final static String PATH_WITH_GRADE_BOOK = PATH + "/{grade-book}";

    protected Student saveStudentToDB() {
        Student student = new Student()
                .setName("Сережа")
                .setAddress("Москва")
                .setPhone("8-920-565-54-52")
                .setEmail("first@ru");
        return studentRepository.save(student);
    }
}
