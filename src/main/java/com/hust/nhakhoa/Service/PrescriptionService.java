package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.DTO.PrescriptionDTO;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Request.PatientRequest;
import com.hust.nhakhoa.Request.PrescriptionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrescriptionService {

    public List<Prescription> getAllPrescription();
    public PrescriptionDTO getPrescriptionById(Integer prescriptionId);
    public PrescriptionDTO addPrescription(PrescriptionRequest prescriptionRequest);
    public PrescriptionDTO addMedicineByPrescription(Integer prescriptionId,Integer medicine);
    public PrescriptionDTO updatePrescription(Integer prescriptionId, PrescriptionRequest prescriptionRequest);
    public Double countTotalPrice(PrescriptionRequest prescriptionRequest);
    public PrescriptionDTO deletePrescription(Integer prescriptionId);
}
