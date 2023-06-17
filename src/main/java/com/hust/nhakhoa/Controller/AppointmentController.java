package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.DTO.AppointmentDTO;
import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Request.AppointmentRequest;
import com.hust.nhakhoa.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dental")

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

  //  @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointment(){
        ResponseEntity<?> resp;

        try {
            List<Appointment> appointmentList = appointmentService.getAllAppointment();
            if(appointmentList != null){
                resp = new ResponseEntity<>(appointmentList, HttpStatus.OK);
            }else{
                resp = new ResponseEntity<String>("NO CONTENT", HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/one/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable("id") Integer id) {
        ResponseEntity<AppointmentDTO> resp;
        try {
            AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(id);
            if(appointmentDTO!=null) {
                resp = new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

  //  @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/save")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody @Valid AppointmentRequest appointment) {
        ResponseEntity<AppointmentDTO> resp;
//        try {
//            if(appointment!=null) {
                resp = new ResponseEntity<AppointmentDTO>(appointmentService.addAppointment(appointment), HttpStatus.CREATED);
//            }else {
//                resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        }catch(Exception e) {
//            resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return resp;

    }

   // @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable("id") Integer id, @RequestBody @Valid AppointmentRequest appointment) {
        return new ResponseEntity<>(appointmentService.updateAppointment(id, appointment), HttpStatus.OK);
    }

  //  @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Integer id) {
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   // @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/{appointmentId}/service/{serviceId}")
    public ResponseEntity<?> addServiceByAppointment(@PathVariable ("appointmentId") Integer appointmentId,
                                                     @PathVariable ("serviceId") Integer serviceId ) {
        ResponseEntity<?> resp;

        try {
            appointmentService.addServiceByAppointment(appointmentId,serviceId);
            resp =  new ResponseEntity<String>("Medicine regiestered with ",HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;

    }
}
