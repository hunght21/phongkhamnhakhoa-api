package com.hust.nhakhoa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "doctor_name")
    private String name;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    //  @JsonManagedReference
    private List<Prescription> prescriptionList;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(nullable = false, unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

}
