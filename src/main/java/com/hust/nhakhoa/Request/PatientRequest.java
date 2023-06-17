package com.hust.nhakhoa.Request;

import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Model.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    private Integer id;
    private String name;
    private String mail;
    private boolean gender;
    private Integer phone;
    //    private String img;
//    private List<Integer> appointmentList;
    private boolean status;
}
