package com.hust.nhakhoa.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPatientRequest {

    private String email;
    private String password;
    private Integer role;
    private boolean gender;
    private Integer phone;
    private String userName;

}
