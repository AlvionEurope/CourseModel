package com.courseModel.controller.professor;

import com.courseModel.controller.BaseTest;
import com.courseModel.entity.Professor;

public class AbstractProfessorTest extends BaseTest {
    protected final static String PATH = "/v1/professor";
    protected final static String PATH_WITH_ID = PATH + "/{id}";

    protected Professor saveProfessorToDB() {
        Professor professor = new Professor()
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        return professorRepository.save(professor);
    }
}
