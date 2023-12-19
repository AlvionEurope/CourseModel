package com.courseModel.controller;

import com.courseModel.entity.Course;
import com.courseModel.entity.Professor;
import com.courseModel.exception.NotFoundException;
import com.courseModel.repository.CourseRepository;
import com.courseModel.repository.ProfessorRepository;
import com.courseModel.repository.StudentRepository;
import com.courseModel.repository.TeachingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class ProfessorControllerMockMvcUnitTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CourseRepository courseRepository;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    TeachingRepository teachingRepository;
    @MockBean
    ProfessorRepository professorRepository;

    @Test
    void create() throws Exception {
        Professor professor = new Professor()
                .setId(3)
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        Mockito.when(professorRepository.save(Mockito.any()))
                .thenReturn(professor);
        mockMvc.perform(
                        post("/v1/professor")
                                .content(objectMapper.writeValueAsString(professor))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));

    }

    @Test
    void givenId_whenGetExistingProfessor_thenStatus200andProfessorReturned() throws Exception {
        Professor professor = new Professor()
                .setId(3)
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        Mockito.when(professorRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(professor));
        mockMvc.perform(
                        get("/v1/professor/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Михаил"));
    }

    @Test
    void givenId_whenGetNotExistingProfessor_thenStatus404anExceptionThrown() throws Exception {
        Mockito.when(professorRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        mockMvc.perform(
                        get("/v1/professor/3"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    void giveProfessor_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Professor professor = new Professor()
                .setId(3)
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        Mockito.when(professorRepository.save(Mockito.any())).thenReturn(professor);
        Mockito.when(professorRepository.findById(Mockito.any())).thenReturn(Optional.of(professor));
        mockMvc.perform(
                        put("/v1/professor/3")
                                .content(objectMapper.writeValueAsString(new Professor()
                                        .setId(3)
                                        .setName("Роман")
                                        .setAddress("Москва")
                                        .setPhone("8-920-456-12-33")
                                        .setPayment(45000)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Роман"));
    }

    @Test
    void givenProfessor_whenDeleteProfessor_thenStatus200() throws Exception {
        Professor professor = new Professor()
                .setId(3)
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        Mockito.when(professorRepository.findById(Mockito.any())).thenReturn(Optional.of(professor));
        mockMvc.perform(
                        delete("/v1/professor/3"))
                .andExpect(status().isOk());
    }

    /**
     * Course
     *
     * @throws Exception
     */
    @Test
    void createCourse() throws Exception {
        Course course = new Course()
                .setNumber(5)
                .setName("JS")
                .setCost(135000);
        Mockito.when(courseRepository.save(Mockito.any()))
                .thenReturn(course);
        mockMvc.perform(
                        post("/v1/course")
                                .content(objectMapper.writeValueAsString(course))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(course)));

    }

    @Test
    void givenId_whenGetExistingCourse_thenStatus200andCourseReturned() throws Exception {
        Course course = new Course()
                .setNumber(5)
                .setName("JS")
                .setCost(135000);
        Mockito.when(courseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(course));
        mockMvc.perform(
                        get("/v1/course/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("5"))
                .andExpect(jsonPath("$.name").value("JS"));
    }

    @Test
    void givenId_whenGetNotExistingCourse_thenStatus404anExceptionThrown() throws Exception {
        Mockito.when(courseRepository.findById(Mockito.any())).
                thenReturn(Optional.empty());
        mockMvc.perform(
                        get("/v1/course/5"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    void giveCourse_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Course course = new Course()
                .setNumber(5)
                .setName("JS")
                .setCost(135000);
        Mockito.when(courseRepository.save(Mockito.any())).thenReturn(course);
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(course));
        mockMvc.perform(
                        put("/v1/course/5")
                                .content(objectMapper.writeValueAsString(new Course()
                                        .setNumber(5)
                                        .setName("Java")
                                        .setCost(125000)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("5"))
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.cost").value("125000.0"));
    }

    @Test
    void givenCourse_whenDeleteCourse_thenStatus200() throws Exception {
        Course course = new Course()
                .setNumber(5)
                .setName("JS")
                .setCost(135000);
        Mockito.when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(course));
        mockMvc.perform(
                        delete("/v1/course/5"))
                .andExpect(status().isOk());
    }
}