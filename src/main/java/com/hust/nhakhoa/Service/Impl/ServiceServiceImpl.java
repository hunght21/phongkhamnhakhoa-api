package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.*;
import com.hust.nhakhoa.Repository.AppointmentRepository;
import com.hust.nhakhoa.Repository.ServiceRepository;
import com.hust.nhakhoa.Request.AppointmentRequest;
import com.hust.nhakhoa.Request.ServiceRequet;
import com.hust.nhakhoa.Service.ServiceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private com.hust.nhakhoa.Model.Service getServiceOrThrow(Integer id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "service id", id));
    }

    @Override
    public List<com.hust.nhakhoa.Model.Service> getAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public com.hust.nhakhoa.Model.Service addService(ServiceRequet serviceRequet) {
        com.hust.nhakhoa.Model.Service service = modelMapper.map(serviceRequet,com.hust.nhakhoa.Model.Service.class);
        return serviceRepository.save(service);
//        com.hust.nhakhoa.Model.Service service = new com.hust.nhakhoa.Model.Service();
//        saveDataToService(service,serviceRequet);
//        serviceRepository.save(service);
//        return modelMapper.map(service,com.hust.nhakhoa.Model.Service.class);
    }

    @Override
    public com.hust.nhakhoa.Model.Service getServiceById(Integer serviceId) {
        com.hust.nhakhoa.Model.Service service = getServiceOrThrow(serviceId);
        return service;
    }

    @Override
    public Double calculateTotalPrice(List<com.hust.nhakhoa.Model.Service> serviceList) {
        return null;
    }

    @Override
    public com.hust.nhakhoa.Model.Service updateService(Integer serviceId, ServiceRequet serviceRequet) {
        com.hust.nhakhoa.Model.Service service = getServiceOrThrow(serviceId);
        saveDataToService(service,serviceRequet);
        modelMapper.map(serviceRequet,service);
        return serviceRepository.save(service);
    }

    @Override
    public void deleteServiceById(Integer serviceId) {
        com.hust.nhakhoa.Model.Service service = getServiceOrThrow(serviceId);
        serviceRepository.delete(service);

    }

    private void saveDataToService(com.hust.nhakhoa.Model.Service service, ServiceRequet serviceRequet) {
        List<Appointment> appointmentList = new ArrayList<>();

        for(Integer id : serviceRequet.getAppointmentList()) {
            Appointment appointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment", "appointment id", id));
            appointmentList.add(appointment);
        }
        service.setName(serviceRequet.getName());
        service.setTime(serviceRequet.getTime());
        service.setPrice(serviceRequet.getPrice());
        service.setDetail(serviceRequet.getDetail());
        service.setAppointmentList(appointmentList);


    }
}
