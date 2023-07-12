package me.rudnikov.backend.dto.create;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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