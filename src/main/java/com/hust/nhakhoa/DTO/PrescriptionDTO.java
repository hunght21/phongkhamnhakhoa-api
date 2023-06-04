package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Doctor;
import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO implements Serializable {

    private int id;
    private String name;
    private Double finalPrice;
    private String note;
    private Patient patient;
    private Doctor doctor;
    private List<Medicine> medicineId;
}
