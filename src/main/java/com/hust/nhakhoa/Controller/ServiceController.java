package com.hust.nhakhoa.Controller;


import com.hust.nhakhoa.Model.Service;
import com.hust.nhakhoa.Request.ServiceRequest;
import com.hust.nhakhoa.Service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllService(){
        ResponseEntity<?> resp;

        try{
            List<Service> serviceList = serviceService.getAllService();
            if( serviceList != null){
                resp = new ResponseEntity<List<Service>>(serviceList, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<String>("Not found", HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable("id") Integer serviceId) {
        ResponseEntity<?> resp;

        try {
            Service service = serviceService.getServiceById(serviceId);
            if (service != null) {
                resp = new ResponseEntity<>(service, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("Not found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/add")
    ResponseEntity<?> addService(@Valid @RequestBody ServiceRequest serviceRequet){
        ResponseEntity<?> resp;
        Service service = serviceService.addService(serviceRequet);

        try{
            resp =  new ResponseEntity<String>("ServiceId registered with "+ service.getId(),HttpStatus.CREATED);
        } catch (Exception e){
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateService(@PathVariable("id") Integer serviceId,@Valid @RequestBody ServiceRequest serviceRequet){

        ResponseEntity<?> resp;
        serviceService.updateService(serviceId,serviceRequet);
        try {
            resp =  new ResponseEntity<String>("ServiceId registered with "+ serviceRequet.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Integer serviceId){
        ResponseEntity<?> resp=null;
        try {
            serviceService.deleteServiceById(serviceId);
            resp = new ResponseEntity<String>("Service "+serviceId+" deleted",HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }
}
