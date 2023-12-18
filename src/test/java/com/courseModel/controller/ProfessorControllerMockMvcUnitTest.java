package com.courseModel.controller;

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
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private TeachingRepository teachingRepository;
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
        Mockito.when(professorRepository.save(Mockito.any())).thenReturn(professor);
        mockMvc.perform(
                        post("/v1/professor")
                                .content(objectMapper.writeValueAsString(professor))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));

    }

    @Test
    public void givenId_whenGetExistingProfessor_thenStatus200andProfessorReturned() throws Exception {
        Professor professor = new Professor()
                .setId(3)
                .setName("Михаил")
                .setAddress("Москва")
                .setPhone("8-920-456-12-33")
                .setPayment(45000);
        Mockito.when(professorRepository.findById(Mockito.any())).thenReturn(Optional.of(professor));
        mockMvc.perform(
                        get("/v1/professor/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Михаил"));
    }

    @Test
    public void givenId_whenGetNotExistingProfessor_thenStatus404anExceptionThrown() throws Exception {
        Mockito.when(professorRepository.findById(Mockito.any())).
                thenReturn(Optional.empty());
        mockMvc.perform(
                        get("/v1/professor/3"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    public void giveProfessor_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
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
    public void givenProfessor_whenDeleteProfessor_thenStatus200() throws Exception {
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
}