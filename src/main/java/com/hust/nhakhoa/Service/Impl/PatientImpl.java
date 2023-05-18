package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Status;
import com.hust.nhakhoa.Repository.PatientRepository;
import com.hust.nhakhoa.Request.AppointmentRequest;
import com.hust.nhakhoa.Request.PatientRequest;
import com.hust.nhakhoa.Service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Patient getPatientOrThrow(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient id", id));
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Integer patientId) {
        return patientRepository.findById(patientId).get();
    }

    @Override
    public Patient addPatient(PatientRequest patientRequest) {
        Patient patient = modelMapper.map(patientRequest,Patient.class);
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Integer patientID, PatientRequest patientRequest) {
        Patient patient = getPatientOrThrow(patientID);
        modelMapper.map(patientRequest,patient);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Integer patientId) {
        Patient patient = getPatientOrThrow(patientId);
        patientRepository.delete(patient);
    }

}
