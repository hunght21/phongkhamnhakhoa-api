package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.Request.ServiceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceService {
    List<com.hust.nhakhoa.Model.Service> getAllService();

    com.hust.nhakhoa.Model.Service addService(ServiceRequest serviceRequet);

    com.hust.nhakhoa.Model.Service getServiceById(Integer serviceId);
    public Double calculateTotalPrice(List<com.hust.nhakhoa.Model.Service> serviceList);

    com.hust.nhakhoa.Model.Service updateService (Integer serviceId, ServiceRequest serviceRequet);

    void deleteServiceById(Integer serviceId);

}
