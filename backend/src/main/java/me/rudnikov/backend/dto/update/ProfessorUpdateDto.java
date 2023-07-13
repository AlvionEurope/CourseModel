package me.rudnikov.backend.dto.update;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorUpdateDto {
    private String fullName;
    private String address;
    private String phoneNumber;
    private Float payment;
}
