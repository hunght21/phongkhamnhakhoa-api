package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Medicine> medicineId;
}
