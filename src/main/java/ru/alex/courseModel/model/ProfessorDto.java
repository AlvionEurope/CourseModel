package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Professor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfessorDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private float payment;

    public ProfessorDto(Professor professor) {
        this.id = professor.getId();
        this.name = professor.getName();
        this.phone = professor.getPhone();
        this.address = professor.getAddress();
        this.payment = professor.getPayment();
    }

    public static List<ProfessorDto> getProfessorDtoList(List<Professor> professors) {
        if (professors == null) {
            return new ArrayList<>();
        }
        List<ProfessorDto> professorDtoList = new ArrayList<>();
        for(Professor professor : professors) {
            professorDtoList.add(new ProfessorDto(professor));
        }
        return professorDtoList;
    }
}
