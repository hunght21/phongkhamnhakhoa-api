package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

    private Integer id;
    private String name;
    private String mail;
    private String password;
    private boolean gender;
    private Integer phone;
    private Integer roleId;
    private boolean status;


}
