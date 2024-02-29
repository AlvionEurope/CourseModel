package com.courseModel.controller;

import com.courseModel.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;


//@ExtendWith(SpringExtension.class)
//@Import({
//        StudentRepository.class,
//        CourseRepository.class,
//        ProfessorRepository.class,
//        TeachingRepository.class,
//        TeachingToScoreRepository.class
//})
//@SpringJUnitConfig
//@WebMvcTest(StudentController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest {
    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected ProfessorRepository professorRepository;
    @Autowired
    protected CourseRepository courseRepository;
    @Autowired
    protected TeachingRepository teachingRepository;
    @Autowired
    protected TeachingToScoreRepository teachingToScoreRepository;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
   protected ObjectMapper objectMapper;

    @PostConstruct
    void beforeAll() {
        cleanBd();
    }

    @AfterEach
    void afterEach() {
        cleanBd();
    }

    void cleanBd() {
        teachingToScoreRepository.deleteAll();
        teachingRepository.deleteAll();
        courseRepository.deleteAll();
        studentRepository.deleteAll();
        professorRepository.deleteAll();
    }
}
