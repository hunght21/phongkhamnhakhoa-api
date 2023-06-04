package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.DTO.PrescriptionDTO;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Request.PrescriptionRequest;
import com.hust.nhakhoa.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/all")
    ResponseEntity<?> getAllPrescription(){
        ResponseEntity<?> resp;
        try {
            List<Prescription> list = prescriptionService.getAllPrescription();
            if(list!=null && !list.isEmpty()) {
                resp = new ResponseEntity<List<Prescription>>(list, HttpStatus.OK);
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
    ResponseEntity<?> getPrescriptionById(@PathVariable("id") Integer prescriptionId){
        ResponseEntity<?> resp;
        try {
            PrescriptionDTO prescriptionDTO = prescriptionService.getPrescriptionById(prescriptionId);
            if(prescriptionDTO!=null) {
                resp = new ResponseEntity<PrescriptionDTO>(prescriptionDTO, HttpStatus.OK);
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
    public ResponseEntity<?> addPrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest){

        ResponseEntity<?> resp;

        PrescriptionDTO prescriptionDTO = prescriptionService.addPrescription(prescriptionRequest);
        try {
            resp =  new ResponseEntity<String>("PrescriptionId registered with "
                    + prescriptionDTO.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePrescription(@PathVariable("id") Integer prescriptionID,
                                                @Valid @RequestBody PrescriptionRequest prescriptionRequest){

        ResponseEntity<?> resp;
        prescriptionService.updatePrescription(prescriptionID,prescriptionRequest);
        try {
            resp =  new ResponseEntity<String>("Prescription registered with "+ prescriptionRequest.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable("id") Integer prescriptionID){
        ResponseEntity<?> resp=null;
        try {
            prescriptionService.deletePrescription(prescriptionID);
            resp = new ResponseEntity<String>("PrescriptionID "+prescriptionID+" deleted",HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/{prescriptionId}/medicine/{medicineId}")
    public ResponseEntity<?> addMedicineByPrescription(@PathVariable ("prescriptionId") Integer prescriptionId,
                                                       @PathVariable ("medicineId") Integer medicineId) {
        ResponseEntity<?> resp;

        try {
            prescriptionService.addMedicineByPrescription(prescriptionId,medicineId);
            resp =  new ResponseEntity<String>("Medicine regiestered with ",HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;

    }

}
