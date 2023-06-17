package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private boolean gender;
    private Integer phone;
//    private String img;
    private boolean status;
    private Integer role;

    public DoctorDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.gender = doctor.isGender();
        this.phone = doctor.getPhone();
 //       this.img = doctor.getEmail();
        this.status = doctor.isStatus();
        this.role = doctor.getRole().getRole_id();
    }
}
