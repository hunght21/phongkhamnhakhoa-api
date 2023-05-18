package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Request.MedicineRequest;
import com.hust.nhakhoa.Request.ServiceRequet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceService {
    List<com.hust.nhakhoa.Model.Service> getAllService();

    com.hust.nhakhoa.Model.Service addService(ServiceRequet serviceRequet);

    com.hust.nhakhoa.Model.Service getServiceById(Integer serviceId);
    public Double calculateTotalPrice(List<com.hust.nhakhoa.Model.Service> serviceList);

    com.hust.nhakhoa.Model.Service updateService (Integer serviceId, ServiceRequet serviceRequet);

    void deleteServiceById(Integer serviceId);

}
