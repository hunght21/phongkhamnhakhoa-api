package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.DTO.PatientDTO;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Request.PatientProfileRequest;
import com.hust.nhakhoa.Request.PatientRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public List<Patient> getAllPatient();
    public Patient getPatientById(Integer patientId);
    public Patient addPatient(PatientRequest patientRequest);
    public Patient updatePatient(Integer patientID, PatientRequest patientRequest);
    public void deletePatient(Integer patientId);

 //   public PatientDTO creatProfile(PatientProfileRequest patientProfileRequest);
}
