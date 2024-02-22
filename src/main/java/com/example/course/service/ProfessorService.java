package com.example.course.service;

import com.example.course.model.Professor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ProfessorService {

    Professor postProfessor(Professor professor);

    Professor getProfessor(Long professorId);

    void deleteProfessor(Long professorId);

    Professor putProfessor(Long professorId, Professor professor) throws Exception;

    void addProfessorCourse(Long professorId, Long courseId);

    ByteArrayInputStream getDataDownloaded() throws IOException;
}
