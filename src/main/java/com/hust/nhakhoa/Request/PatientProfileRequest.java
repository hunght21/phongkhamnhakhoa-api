package com.hust.nhakhoa.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientProfileRequest {
    private int id;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String gender;
    private Integer userID;
}
