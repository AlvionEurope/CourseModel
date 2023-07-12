package me.rudnikov.backend.dto.create;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfessorCreateDto {
    private String fullName;
    private String address;
    private String phoneNumber;
    private Float payment;
}