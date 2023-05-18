package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.DTO.AppointmentDTO;

import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Request.AppointmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    public List<Appointment> getAllAppointment();
    public AppointmentDTO getAppointmentById(Integer appointmentId);
    public AppointmentDTO addAppointment(AppointmentRequest appointmentRequest);
    public AppointmentDTO addServiceByAppointment(Integer appointmentId, Integer serviceId);
    public AppointmentDTO updateAppointment(Integer appointmentId, AppointmentRequest appointmentRequest);
    public AppointmentDTO deleteAppointmentById(Integer appointmentId);
}
