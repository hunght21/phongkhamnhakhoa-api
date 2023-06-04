package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Doctor;
import com.hust.nhakhoa.Repository.DoctorRepository;
import com.hust.nhakhoa.Request.DoctorRequest;
import com.hust.nhakhoa.Service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.print.Doc;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Doctor getDoctorOrThrow(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctor id", id));
    }
    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Integer doctorId) {
        Doctor doctor = getDoctorOrThrow(doctorId);
        return doctor;
    }

    @Override
    public Doctor addDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = modelMapper.map(doctorRequest, Doctor.class);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Integer doctorId, DoctorRequest doctorRequest) {

        Doctor doctor = getDoctorOrThrow(doctorId);
        modelMapper.map(doctorRequest,doctor);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Integer doctorId) {
        Doctor doctor = getDoctorOrThrow(doctorId);
        doctorRepository.delete(doctor);
    }
}
