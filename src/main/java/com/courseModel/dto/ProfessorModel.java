package com.courseModel.dto;

import com.courseModel.entity.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfessorModel {
    private int id;
    private String name;
    private String address;
    private String phone;
    private float payment;

    public static ProfessorModel toModel(Professor professor) {
        ProfessorModel professorModel = new ProfessorModel();
        professorModel.setId(professor.getId());
        professorModel.setName(professor.getName());
        professorModel.setAddress(professor.getAddress());
        professorModel.setPhone(professor.getPhone());
        professorModel.setPayment(professor.getPayment());
        return professorModel;
    }

    public static List<ProfessorModel> readAllModels(List<Professor> professorList) {
        List<ProfessorModel> professorModels = new ArrayList<>();
        for (Professor professor : professorList) {
            professorModels.add(ProfessorModel.toModel(professor));
        }
        return professorModels;
    }
}
