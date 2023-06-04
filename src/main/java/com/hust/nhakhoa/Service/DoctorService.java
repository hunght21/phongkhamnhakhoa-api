package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.Model.Doctor;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Request.DoctorRequest;
import com.hust.nhakhoa.Request.PatientRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    public List<Doctor> getAllDoctor();
    public Doctor getDoctorById(Integer doctorId);
    public Doctor addDoctor(DoctorRequest doctorRequest);
    public Doctor updateDoctor(Integer doctorId, DoctorRequest doctorRequest);
    public void deleteDoctor(Integer doctorId);
}
