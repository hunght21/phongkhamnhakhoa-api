package com.hust.nhakhoa.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    private int id;
    private String name;
    private String password;
    private String email;
    private boolean gender;
    private Integer phone;
//    private String img;
    private boolean status;
    private Integer role;
}
