package me.rudnikov.backend.dto.create;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentCreateDto {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer recordBook;
}