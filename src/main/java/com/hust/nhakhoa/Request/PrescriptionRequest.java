package com.hust.nhakhoa.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Patient;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequest {
    private int id;
    private String name;
    private Double finalPrice;
    private String note;
    private Integer patientId;
    private List<Integer> medicineId;

}
