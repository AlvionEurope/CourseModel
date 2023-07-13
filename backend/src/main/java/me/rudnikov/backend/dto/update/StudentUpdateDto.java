package me.rudnikov.backend.dto.update;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentUpdateDto {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer recordBook;
}