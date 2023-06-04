package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.Model.Gender;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Request.PatientRequest;
import com.hust.nhakhoa.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllPatient(){
        ResponseEntity<?> resp;
        try {
            List<Patient> list = patientService.getAllPatient();
            if(list!=null && !list.isEmpty()) {
                resp = new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<String>("No Record Found",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable("id") Integer id){
        ResponseEntity<?> resp;
        try {
            Patient patient = patientService.getPatientById(id);
            if(patient!=null) {
                resp = new ResponseEntity<Patient>(patient, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<String>("No Data Found",(HttpStatus.NO_CONTENT));
            }
        }catch(Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientRequest patientRequest){

        ResponseEntity<?> resp;

       Patient patient = patientService.addPatient(patientRequest);
        try {
            resp =  new ResponseEntity<String>("PatientId registered with "+ patient.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") Integer patientID,@Valid @RequestBody PatientRequest patientRequest){

        ResponseEntity<?> resp;
        Patient existingPatient = patientService.getPatientById(patientID);
        existingPatient.setId(patientID);
        existingPatient.setName(patientRequest.getName());
        existingPatient.setEmail(patientRequest.getEmail());
        existingPatient.setAddress(patientRequest.getAddress());
        existingPatient.setPhoneNumber(patientRequest.getPhoneNumber());
        existingPatient.setDateOfBirth(patientRequest.getDateOfBirth());
        existingPatient.setGender(Gender.valueOf(patientRequest.getGender()));
        patientService.updatePatient(patientID,patientRequest);
        try {
            resp =  new ResponseEntity<String>("PatientId registered with "+ patientRequest.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Integer patientId){
        ResponseEntity<?> resp=null;
        try {
            patientService.deletePatient(patientId);
            resp = new ResponseEntity<String>("Patient "+patientId+" deleted",HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }
}
