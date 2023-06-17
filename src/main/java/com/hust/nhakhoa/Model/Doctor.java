package com.hust.nhakhoa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "doctor")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Users{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "doctor_name")
//    private String name;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(name = "status", nullable = false)
//    private boolean status;
//
////    @Column(name = "img", nullable = false)
////    private String img;
//
//    @Column(name = "gender", nullable = false)
//    @NotNull(message = "Gender cannot be null")
//    private boolean gender;
//
//
//    private Integer role_id;
//
//    @OneToOne
//    @JoinColumn(name = "account_id")
//    private Users account;

//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    //  @JsonManagedReference
//    private List<Prescription> prescriptionList;

    public Doctor(int id, String name, String password, String email, Role role, boolean status, boolean gender, Integer phone, String img, List<Appointment> appointmentList) {
        super(id, name, password, email, role, status, gender, phone, img);
        this.appointmentList = appointmentList;
    }
}
