package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.Model.Doctor;
import com.hust.nhakhoa.Model.Gender;
import com.hust.nhakhoa.Request.DoctorRequest;
import com.hust.nhakhoa.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllDoctor(){
        ResponseEntity<?> resp;
        try {
            List<Doctor> list = doctorService.getAllDoctor();
            if(list!=null && !list.isEmpty()) {
                resp = new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<String>("No Record Found",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable("id") Integer id){
        ResponseEntity<?> resp;
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if(doctor!=null) {
                resp = new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
            }else {
                resp = new ResponseEntity<String>("No Data Found",(HttpStatus.NO_CONTENT));
            }
        }catch(Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorRequest doctorRequest){

        ResponseEntity<?> resp;

        Doctor doctor = doctorService.addDoctor(doctorRequest);
        try {
            resp =  new ResponseEntity<String>("DoctorId registered with "+ doctor.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") Integer doctorID,@Valid @RequestBody DoctorRequest doctorRequest){

        ResponseEntity<?> resp;
        Doctor existingDoctor = doctorService.getDoctorById(doctorID);
        existingDoctor.setId(doctorID);
        existingDoctor.setName(doctorRequest.getName());
        existingDoctor.setEmail(doctorRequest.getEmail());
        existingDoctor.setPhoneNumber(doctorRequest.getPhoneNumber());
        existingDoctor.setGender(Gender.valueOf(doctorRequest.getGender()));
        doctorService.updateDoctor(doctorID,doctorRequest);
        try {
            resp =  new ResponseEntity<String>("DoctorId registered with "+ doctorRequest.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Integer doctorId){
        ResponseEntity<?> resp=null;
        try {
            doctorService.deleteDoctor(doctorId);
            resp = new ResponseEntity<String>("Doctor "+doctorId+" deleted",HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }
}

