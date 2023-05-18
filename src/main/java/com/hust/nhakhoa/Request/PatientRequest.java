package com.hust.nhakhoa.Request;

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

    private int id;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String Email;
    private String gender;
}
