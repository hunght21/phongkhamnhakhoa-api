package com.hust.nhakhoa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

    private int id;
    private String name;
}
