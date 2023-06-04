package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.AppointmentDTO;
import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.*;
import com.hust.nhakhoa.Repository.*;
import com.hust.nhakhoa.Request.AppointmentRequest;
import com.hust.nhakhoa.Service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AppointmentImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Appointment getAppointmentOrThrow(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "appointment id", id));
    }
    private com.hust.nhakhoa.Model.Service getServiceOrThrow(Integer id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "service id", id));
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public AppointmentDTO getAppointmentById(Integer appointmentId) {
        return modelMapper.map(getAppointmentOrThrow(appointmentId),AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO addAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        saveDataToAppointment(appointment,appointmentRequest);
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO addServiceByAppointment(Integer appointmentId, Integer serviceId) {
        Appointment appointment = getAppointmentOrThrow(appointmentId);
        com.hust.nhakhoa.Model.Service service = getServiceOrThrow(serviceId);
        appointment.getServices().add(service);
        service.getAppointmentList().add(appointment);
//        appointment.se(medicineService.calculateTotalPrice(prescription.getMedicineList()));
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO updateAppointment(Integer appointmentId, AppointmentRequest appointmentRequest) {
        Appointment appointment = getAppointmentOrThrow(appointmentId);
        saveDataToAppointment(appointment,appointmentRequest);
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO deleteAppointmentById(Integer appointmentId) {
        Appointment appointment = new Appointment();
        appointmentRepository.delete(appointment);
        return modelMapper.map(appointment,AppointmentDTO.class);
    }


    private void saveDataToAppointment(Appointment appointment, AppointmentRequest appointmentRequest) {
        List<com.hust.nhakhoa.Model.Service> services = new ArrayList<>();

        for(Integer id : appointmentRequest.getServiceIds()) {
            com.hust.nhakhoa.Model.Service service = serviceRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Service", "service id", id));
            services.add(service);
        }

        Users employee = userRepository.findById(appointmentRequest.getEmployeeId())
                .orElseThrow(() ->new ResourceNotFoundException("Employee", "employee id", appointmentRequest.getEmployeeId()));;
        Patient patient = patientRepository.findById(appointmentRequest.getPatientId())
                .orElseThrow(() ->new ResourceNotFoundException("Patient", "patient id", appointmentRequest.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId())
                .orElseThrow(() ->new ResourceNotFoundException("Doctor", "patient id", appointmentRequest.getPatientId()));
//        if(employee.hasRole(Role.EMPLOYEE)) {
            appointment.setServices(services);
 //           appointment.setEmployee(employee);
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setStartTime(appointmentRequest.getStartTime());
            appointment.setEndTime(appointmentRequest.getEndTime());
            appointment.setNotes(appointmentRequest.getNotes());
            appointment.setStatus(Status.valueOf(appointmentRequest.getStatus()));
  //      }

    }
}
