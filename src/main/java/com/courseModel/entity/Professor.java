package com.courseModel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private float payment;

    public Professor(String name, String address, String phone, float payment) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
    }
}
