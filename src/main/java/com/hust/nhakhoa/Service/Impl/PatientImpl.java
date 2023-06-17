package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.AppointmentDTO;
import com.hust.nhakhoa.DTO.PatientDTO;
import com.hust.nhakhoa.DTO.PrescriptionDTO;
import com.hust.nhakhoa.Exceptions.EmailNotAvailableException;
import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.*;
import com.hust.nhakhoa.Repository.PatientRepository;
import com.hust.nhakhoa.Repository.UserRepository;
import com.hust.nhakhoa.Request.PatientProfileRequest;
import com.hust.nhakhoa.Request.PatientRequest;
import com.hust.nhakhoa.Request.PrescriptionRequest;
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
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Patient getPatientOrThrow(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patient id", id));
    }

    private void checkEmailUniqueness(String email) {
        if (patientRepository.existsByEmail(email)) {
            throw new EmailNotAvailableException(email);
        }
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

//    @Override
//    public PatientDTO creatProfile(PatientProfileRequest patientProfileRequest) {
//        Patient patient = new Patient();
//        saveDataToPatient(patient,patientProfileRequest);
//        patientRepository.save(patient);
//        return modelMapper.map(patient, PatientDTO.class);
//    }

//    private void saveDataToPatient(Patient patient, PatientProfileRequest patientProfileRequest) {
//
//
//        Users users = userRepository.findById(patientProfileRequest.getUserID())
//                .orElseThrow(() ->new ResourceNotFoundException("Patient", "patient id", patientProfileRequest.getUserID()));
//
//        patient.setName(patientProfileRequest.getName());
//        patient.setAddress(patientProfileRequest.getAddress());
//        patient.setPhoneNumber(patientProfileRequest.getPhoneNumber());
//        patient.setDateOfBirth(patientProfileRequest.getDateOfBirth());
//        patient.setGender(Gender.valueOf(patientProfileRequest.getGender()));
//        patient.setUser(users);
//
//
//
//    }

}
