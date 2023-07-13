package me.rudnikov.backend.entity;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity(
        name = "professor"
)
@Table(
        name = "professors"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {
    @Id
    @SequenceGenerator(
            name = "professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "professor_sequence"
    )
    @Column(
            name = "professor_id"
    )
    private Long id;

    @Column(
            name = "professor_full_name"
    )
    private String fullName;

    @Column(
            name = "professor_address"
    )
    private String address;

    @Column(
            name = "professor_phone_number",
            unique = true
    )
    private String phoneNumber;

    @Column(
            name = "professor_payment"
    )
    private Float payment;

    @OneToMany(
            mappedBy = "professor",
            fetch = FetchType.EAGER
    )
    private List<Course> courses;
}